create table wellplate.health (
                                  health_id uuid not null,
                                  wellness_challenger_id uuid not null,
                                  base_measurement_unit varchar(255),
                                  base_body_weight numeric(38,2),
                                  goal_measurement_unit varchar(255),
                                  goal_body_weight numeric(38,2),
                                  primary key (health_id)
)