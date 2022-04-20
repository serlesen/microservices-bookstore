create table if not exists service_users.bookstore_user (
    id bigserial primary key,
    login text not null,
    password text not null,
    birth_date date not null,
    age bigint not null
);

insert into service_users.bookstore_user (login, password, birth_date, age)
values ('sergio', '$2a$10$xzYpKFy48SanpwyfxPp/p.lqQDHaeCBgZjm/xgpc74CGzIBUftffy', '1960-01-01', 25); -- encoded 'the-password'
