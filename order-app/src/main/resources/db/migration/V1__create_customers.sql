create table customers (
    id uuid primary key,
    email varchar(255) not null unique,
    first_name varchar(100) not null,
    last_name varchar(100) not null,
    status varchar(30) not null,
    created_at timestamp with time zone not null
);
