create table if not exists wellplate.auth_member (
    member_id uuid primary key not null,
    login_id varchar(255),
    password varchar(255),
    member_type varchar(255)
    );
