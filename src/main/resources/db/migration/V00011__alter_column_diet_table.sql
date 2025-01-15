alter table wellplate.diet
    alter column meal_time type timestamp using meal_time::timestamp;
