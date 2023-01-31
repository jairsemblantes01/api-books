create table if not exists authors (
            id serial UNIQUE PRIMARY KEY NOT NULL,
            first_name varchar(64) NOT NULL,
            last_name varchar(64) NOT NULL
        );

create table if not exists books
(
    id serial UNIQUE NOT NULL PRIMARY KEY,
    isbn varchar(16) NOT NULL,
    title varchar(128) NOT NULL,
    author integer NOT NULL REFERENCES authors(id) ,
    price float8 NOT NULL
);