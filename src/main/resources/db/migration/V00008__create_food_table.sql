create table if not exists wellplate.food
(
    food_code  varchar(255) not null
    primary key,
    food_name  varchar(255),
    calorie    numeric(38, 2),
    size       numeric(38, 2),
    size_unit  varchar(255),
    maker_name varchar(255)
    );

comment on column wellplate.food.food_code is '식품 코드';

comment on column wellplate.food.calorie is '식품 열량(kcal)';

comment on column wellplate.food.food_name is '식품명';

comment on column wellplate.food.maker_name is '제조사명';

comment on column wellplate.food.size is '식품중량';

comment on column wellplate.food.size_unit is '식품중량단위';

INSERT INTO wellplate.food (food_code, food_name, calorie, size, size_unit, maker_name) VALUES ('P101-102000100-0185', '수제바닐라마카롱', 467.00, 30.00, 'g', '상수당');
INSERT INTO wellplate.food (food_code, food_name, calorie, size, size_unit, maker_name) VALUES ('P101-102000100-0186', '수제복숭아마카롱', 417.00, 30.00, 'g', '상수당');
INSERT INTO wellplate.food (food_code, food_name, calorie, size, size_unit, maker_name) VALUES ('P101-102000100-0187', '수제블루베리요거트마카롱', 417.00, 30.00, 'g', '상수당');
INSERT INTO wellplate.food (food_code, food_name, calorie, size, size_unit, maker_name) VALUES ('P101-102000100-0188', '수제순우유마카롱', 483.00, 30.00, 'g', '상수당');
