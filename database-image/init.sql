CREATE ROLE service_books WITH LOGIN PASSWORD 'srv-bks';
CREATE ROLE service_prices WITH LOGIN PASSWORD 'srv-prcs';
CREATE ROLE service_users WITH LOGIN PASSWORD 'srv-usrs';

CREATE SCHEMA service_books AUTHORIZATION service_books;
CREATE SCHEMA service_prices AUTHORIZATION service_prices;
CREATE SCHEMA service_users AUTHORIZATION service_users;


