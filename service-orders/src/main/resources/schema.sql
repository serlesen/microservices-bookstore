create table if not exists service_orders.order (
    id bigserial primary key,
    price numeric(10, 4) not null,
    book_id bigint not null,
    created_date timestamp not null
);

create sequence if not exists service_orders.seq_order start 100 increment 1;
