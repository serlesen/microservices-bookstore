CREATE ROLE service_books WITH LOGIN PASSWORD 'srv-bks';
CREATE ROLE service_orders WITH LOGIN PASSWORD 'srv-ordrs';
CREATE ROLE service_prices WITH LOGIN PASSWORD 'srv-prcs';

CREATE SCHEMA service_books AUTHORIZATION service_books;
CREATE SCHEMA service_orders AUTHORIZATION service_orders;
CREATE SCHEMA service_prices AUTHORIZATION service_prices;


