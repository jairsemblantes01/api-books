create table if not exists public.authors (
            id serial UNIQUE,
            first_name varchar(64) NOT NULL,
            last_name varchar(64) NOT NULL,
            CONSTRAINT authors_id_pkey PRIMARY KEY (id)
        );

create table if not exists public.books
(
    id serial UNIQUE,
    isbn varchar(16) NOT NULL,
    title varchar(128) NOT NULL,
    author integer NOT NULL,

    price float8 NOT NULL,
    CONSTRAINT id PRIMARY KEY (id),
    CONSTRAINT fk_author FOREIGN KEY (author)
        REFERENCES authors (id)
);