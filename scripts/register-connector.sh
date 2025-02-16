#!/bin/bash

# 0) 대기 함수: Kafka Connect 8083 열릴 때까지 기다리는 로직
wait_for_kafka_connect() {
  echo "Waiting for Kafka Connect to be ready on port 8083..."
  until curl -s http://kafka-connect:8083/connectors >/dev/null 2>&1; do
    sleep 3
  done
  echo "Kafka Connect is up!"
}

# 1) 실제 등록할 Debezium Connector 설정 JSON
#   실제로는 아래 config를 필요에 맞게 수정하세요.
CONNECTOR_CONFIG='
{
  "name": "my-debezium-connector",
  "config": {
    "connector.class": "io.debezium.connector.postgresql.PostgresConnector",
    "tasks.max": "1",

    "database.hostname": "postgres-db",
    "database.port": "5432",
    "database.user": "wellplate",
    "database.password": "wellplate1234",
    "database.dbname": "wellplate",
    "database.server.name": "wellplate-server",
    "plugin.name": "pgoutput",

    "slot.name": "wellplate_outbox_slot",
    "publication.name": "wellplate_publication",

    "table.include.list": "wellplate.membership_outbox_event",


    "transforms": "outbox",
    "transforms.outbox.type": "io.debezium.transforms.outbox.EventRouter",
    "transforms.outbox.route.by.field": "topic",
    "transforms.outbox.route.topic.replacement": "${routedByValue}",

    "transforms.outbox.table.fields.additional.placement":"event_type:header:eventType",

    "transforms.outbox.table.field.event.id": "member_ship_outbox_event_id",
    "transforms.outbox.table.field.event.key": "aggregate_id",
    "transforms.outbox.table.field.event.payload": "payload",
    "transforms.outbox.table.field.event.type": "event_type",

    "key.converter": "org.apache.kafka.connect.json.JsonConverter",
    "value.converter": "org.apache.kafka.connect.json.JsonConverter",
    "key.converter.schemas.enable": "false",
    "value.converter.schemas.enable": "false",

    "topic.prefix": "membership-outbox-"
  }
}
'

# 2) 메인 실행
#wait_for_kafka_connect

echo "Registering Debezium connector with config:"
echo "$CONNECTOR_CONFIG"

curl -X POST http://kafka-connect:8083/connectors \
  -H "Content-Type: application/json" \
  -d "$CONNECTOR_CONFIG"

echo "Connector registration request done."

# optional: check connector status
sleep 5
echo "Connector status:"
curl -s http://kafka-connect:8083/connectors/my-debezium-connector/status
