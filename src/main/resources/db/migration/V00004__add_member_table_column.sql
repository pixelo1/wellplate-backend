alter table wellplate.member add column member_type varchar(255);
comment on column wellplate.member.member_type IS '회원 타입'