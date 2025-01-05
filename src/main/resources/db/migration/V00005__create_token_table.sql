create table wellplate.token
(
    expired    boolean not null,
    revoked    boolean not null,
    member_id  uuid,
    token_id   uuid    not null
        primary key,
    token      varchar(255)
        unique,
    token_type varchar(255)
    );