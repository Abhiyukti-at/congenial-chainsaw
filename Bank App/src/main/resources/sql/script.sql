-- This script is used to create the database and tables for the application for default setup in jdbc
use bank_app_db;
create table users(username varchar(50) not null primary key,password varchar(500) not null,enabled boolean not null);
create table authorities (username varchar(50) not null,authority varchar(50) not null,constraint fk_authorities_users foreign key(username) references users(username));
create unique index ix_auth_username on authoritiauthoritieses (username,authority);
INSERT INTO users (username, password, enabled) VALUES
('user', '{bcrypt}$2a$12$UisLpw2uJVXq4yEiRNsmmeD/e1RcOSdWKB9RfllB2c.oZifhtwh4S', true),
('admin', '{noop}Admin@7877!', true);
-- Insert into authorities table
INSERT INTO authorities (username, authority) VALUES
('user', 'read'),
('admin', 'admin');

--Create and insert for Customer table

CREATE TABLE customer (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(150) NOT NULL UNIQUE,
    role VARCHAR(50) NOT NULL
);

INSERT INTO customer (name, password, email, role) VALUES
('Nikita Gupta', '{bcrypt}$2a$12$UisLpw2uJVXq4yEiRNsmmeD/e1RcOSdWKB9RfllB2c.oZifhtwh4S', 'nikita.gupta@example.com', 'USER'),
('Admin User', '{noop}Admin@7877!', 'admin@example.com', 'ADMIN');