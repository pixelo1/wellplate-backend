create table wellplate.health (
                                  health_id uuid not null,
                                  base_body_weight jsonb,
                                  goal_body_weight jsonb,
                                  primary key (health_id)
)