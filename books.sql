-- Table: public.books

-- DROP TABLE public.books;

CREATE TABLE public.books
(
    isbn_13 character varying(13) COLLATE pg_catalog."default" NOT NULL,
    title character varying(100) COLLATE pg_catalog."default",
    author character varying(80) COLLATE pg_catalog."default",
    publish_date date,
    price numeric(6,2),
    content bytea,
    CONSTRAINT books_pkey PRIMARY KEY (isbn_13)
)

TABLESPACE pg_default;

ALTER TABLE public.books
    OWNER to postgres;
