--liquibase formatted sql

--changeset author:ananthupm:1
DROP TABLE IF EXISTS parent;

CREATE TABLE IF NOT EXISTS public.parent
(
    uuid uuid NOT NULL,
    address character varying(255) COLLATE pg_catalog."default",
    email character varying(255) COLLATE pg_catalog."default",
    father_name character varying(255) COLLATE pg_catalog."default",
    mother_name character varying(255) COLLATE pg_catalog."default",
    primary_contact character varying(255) COLLATE pg_catalog."default",
    secondary_contact character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT parent_pkey PRIMARY KEY (uuid),
    CONSTRAINT uk_akav6ukpa74f5wduao4gtletv UNIQUE (primary_contact)
)
