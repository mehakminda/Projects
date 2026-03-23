use testdb;
CREATE TABLE customer (
    id INT PRIMARY KEY,
    city VARCHAR(50),
    first_name VARCHAR(100),
    last_name VARCHAR(100)
);


INSERT INTO customer (id, city, first_name, last_name) VALUES
(1, 'Pune', 'Deb', 'Mukherji'),
(3, 'Berlin', 'pughaz JavaBro', 'enth y'),
(4, 'Dubai', 'Sherin', 'Mohammed'),
(5, 'houston', 'hari', 'priya'),
(7, 'London', 'tejendra', 'singh'),
(9, 'USA', 'Jonas', 'charlotee'),
(10, 'Mumbai', 'Navin', 'reddy');
