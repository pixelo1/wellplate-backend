create table if not exists wellplate.member (
    member_id uuid primary key not null,
    email varchar(255),
    password varchar(255)
    );
