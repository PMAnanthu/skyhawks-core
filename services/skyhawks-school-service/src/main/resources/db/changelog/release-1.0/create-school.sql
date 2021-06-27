--liquibase formatted sql

--changeset author:ananthupm:1
DROP TABLE IF EXISTS school;

CREATE TABLE IF NOT EXISTS public.school
(
    uuid uuid NOT NULL,
    active boolean NOT NULL,
    address character varying(255) COLLATE pg_catalog."default",
    education character varying(255) COLLATE pg_catalog."default",
    email character varying(255) COLLATE pg_catalog."default",
    manger_id character varying(255) COLLATE pg_catalog."default",
    name character varying(255) COLLATE pg_catalog."default",
    school_code character varying(255) COLLATE pg_catalog."default",
    web_site character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT school_pkey PRIMARY KEY (uuid),
    CONSTRAINT uk_kshhmdqjvf80nyraai0m8902s UNIQUE (school_code)
)
