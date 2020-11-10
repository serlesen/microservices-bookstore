create table if not exists service_books.author (
    id bigserial primary key,
    first_name text not null,
    last_name text not null
);

insert into service_books.author (id, first_name,last_name)
values (1, 'Stephen', 'King'),
       (2, 'Dan', 'Brown')
ON CONFLICT (id) DO NOTHING;

create sequence if not exists service_books.seq_author start 100 increment 1;

create table if not exists service_books.book (
    id bigserial primary key,
    title text not null,
    author_id bigint not null references service_books.author(id)
);

insert into service_books.book (id, title, author_id)
values (1, 'It', 1),
       (2, 'Da Vinci Code', 2)
ON CONFLICT (id) DO NOTHING;

create sequence if not exists service_books.seq_book start 100 increment 1;
