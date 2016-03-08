CREATE TABLE pizza (id SERIAL PRIMARY KEY, name VARCHAR(50), type VARCHAR(50),  price REAL);
CREATE TABLE orders (id SERIAL PRIMARY KEY, customer_id INTEGER, state VARCHAR(50));
CREATE TABLE customer (id SERIAL PRIMARY KEY, name VARCHAR(50), address VARCHAR(200));
CREATE TABLE order_pizza (order_id INTEGER, pizza_id INTEGER,  price REAL);