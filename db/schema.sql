create table if not exists rabbit (
    created_date timestamp
);

create table if not exists post (
    id serial primary key,
    name varchar(100),
    text text,
    link varchar(255) unique,
    created timestamp
);