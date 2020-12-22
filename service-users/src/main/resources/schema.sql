create table if not exists service_users.bookstore_user (
    id bigserial primary key,
    login text not null,
    password text not null
);

insert into service_users.bookstore_user (login, password)
values ('sergio', '$2a$10$xzYpKFy48SanpwyfxPp/p.lqQDHaeCBgZjm/xgpc74CGzIBUftffy'); -- encoded 'the-password'
