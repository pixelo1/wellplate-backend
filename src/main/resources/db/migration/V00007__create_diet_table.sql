create table if not exists wellplate.diet
(
    diet_id                uuid not null
    primary key,
    food_info              jsonb,
    health_id              uuid,
    wellness_challenger_id uuid,
    meal_time              date
);

comment on column wellplate.diet.food_info is '음식 정보';
comment on column wellplate.diet.meal_time is '섭취 시간';
