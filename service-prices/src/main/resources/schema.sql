create table if not exists service_prices.price (
    id bigserial primary key,
    book_id bigint not null,
    price numeric(10, 4) not null
);

insert into service_prices.price (id, book_id, price)
values (1, 1, 15.00),
       (2, 2, 20.00)
ON CONFLICT (id) DO NOTHING;

create sequence if not exists service_prices.seq_price start 100 increment 1;
