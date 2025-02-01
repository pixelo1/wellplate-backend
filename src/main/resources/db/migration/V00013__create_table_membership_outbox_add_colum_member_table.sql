alter table wellplate.member add column registration_status varchar(255);

comment on column wellplate.member.registration_status is '회원가입 상태';

create table if not exists wellplate.membership_outbox_event
(
    member_ship_outbox_event_id uuid not null
    primary key,
    aggregate_id                uuid,
    aggregate_type              varchar(255),
    created_at                  timestamp(6),
    event_type                  varchar(255),
    payload                     jsonb,
    topic                       varchar(255)
    );

comment on column wellplate.membership_outbox_event.aggregate_id is 'aggregate ID';

comment on column wellplate.membership_outbox_event.aggregate_type is '이벤트 출처';

comment on column wellplate.membership_outbox_event.created_at is '생성일시';

comment on column wellplate.membership_outbox_event.event_type is '이벤트 명';

comment on column wellplate.membership_outbox_event.payload is '이벤트 내용';

comment on column wellplate.membership_outbox_event.topic is '이벤트 토픽';
