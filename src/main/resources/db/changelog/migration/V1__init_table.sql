create table if not exists public.users
(
    id         bigserial primary key,
    first_name varchar(100)  not null,
    last_name  varchar(100)  not null,
    email      varchar(40)   not null unique,
    password   varchar(1000) not null,
    role       varchar(100)  not null,
    created_at timestamp,
    updated_at timestamp default null
);


