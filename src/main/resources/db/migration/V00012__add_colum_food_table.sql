alter table wellplate.food add column nutrition_base_amount numeric;
alter table wellplate.food add column nutrition_base_unit varchar(20);

comment on column wellplate.food.nutrition_base_amount is '영양소 기준량';

comment on column wellplate.food.nutrition_base_unit is '영양소 기준 단위';
