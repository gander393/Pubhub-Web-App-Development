-- Table: public.book_tags

-- DROP TABLE public.book_tags;

CREATE TABLE public.book_tags
(
    tag_name character varying(300) COLLATE pg_catalog."default" NOT NULL,
    isbn_13 character varying(13) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT tag_name PRIMARY KEY (tag_name),
    CONSTRAINT isbn_13 FOREIGN KEY (isbn_13)
        REFERENCES public.books (isbn_13) MATCH FULL
        ON UPDATE SET DEFAULT
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE public.book_tags
    OWNER to postgres;
-- Index: fki_isbn_13

-- DROP INDEX public.fki_isbn_13;

CREATE INDEX fki_isbn_13
    ON public.book_tags USING btree
    (isbn_13 COLLATE pg_catalog."default" ASC NULLS LAST)
    TABLESPACE pg_default;
