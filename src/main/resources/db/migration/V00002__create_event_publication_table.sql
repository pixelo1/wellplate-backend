create table if not exists public.event_publication (
    completion_date timestamp(6) with time zone,
    publication_date timestamp(6) with time zone,
    id uuid primary key not null,
    event_type character varying(255),
    listener_id character varying(255),
    serialized_event character varying(32672)
    );
