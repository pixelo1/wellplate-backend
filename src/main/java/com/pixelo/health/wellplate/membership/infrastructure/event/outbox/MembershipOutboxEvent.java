package com.pixelo.health.wellplate.membership.infrastructure.event.outbox;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor
@Entity
@Table(name = "membership_outbox_event", schema = "wellplate")
public class MembershipOutboxEvent {

    @Id
    private UUID memberShipOutboxEventId;

    @Comment("이벤트 출처")
    @Column(name = "aggregate_type")
    private String aggregateType; // member

    @Comment("aggregate ID")
    @Column(name = "aggregate_id")
    private UUID aggregateId; // 해당 aggregate id

    @Comment("이벤트 토픽")
    private String topic;

    @Comment("이벤트 명")
    @Column(name = "event_type")
    private String eventType; //  MEMBER_CREATED

    @Comment("이벤트 내용")
    @JdbcTypeCode(SqlTypes.JSON)
    private String payload;

    @Comment("생성일시")
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Builder
    public MembershipOutboxEvent(String aggregateType,
                                 UUID aggregateId,
                                 String topic,
                                 String eventType,
                                 String payload) {


        this.memberShipOutboxEventId = UUID.randomUUID();
        this.aggregateType = aggregateType;
        this.aggregateId = aggregateId;
        this.topic = topic;
        this.eventType = eventType;
        this.payload = payload;
        this.createdAt = LocalDateTime.now();
    }
}
