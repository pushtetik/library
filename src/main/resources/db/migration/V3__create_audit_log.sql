create table audit_log (
                           id bigserial primary key,
                           action varchar(255) not null,
                           created_at timestamp default now() not null
);