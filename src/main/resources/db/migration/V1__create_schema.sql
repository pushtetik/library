create table "user"
(
    id            bigint    primary key,
    email         varchar(255)                                        not null
        constraint users_email_key
            unique,
    password_hash varchar(100)                                        not null,
    full_name     varchar(200)                                        not null,
    role          varchar(20)                                         not null
        constraint users_role_check
            check ((role)::text = ANY ((ARRAY ['USER'::character varying, 'ADMIN'::character varying])::text[])),
    created_at    timestamp default now()                             not null
);


create table book
(
    id             bigserial
        primary key,
    title          varchar(200)            not null,
    author         varchar(200)            not null,
    isbn           varchar(20)
        unique,
    published_year integer
        constraint book_published_year_check
            check ((published_year >= 0) AND (published_year <= 2100)),
    created_at     timestamp default now() not null
);


create table loan
(
    id          bigserial
        primary key,
    book_id     bigint                    not null
        references book,
    user_id     bigint                    not null
        references "user",
    loan_date   date default CURRENT_DATE not null,
    due_date    date                      not null,
    return_date date
);



create index loan_book_id_index
    on loan (book_id);

create index loan_user_id_index
    on loan (user_id);

create unique index loan_book_id
    on loan (book_id)
    where (return_date IS NULL);

