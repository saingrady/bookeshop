-- Category table
DROP TABLE IF EXISTS category;

CREATE TABLE category (
  id int NOT NULL PRIMARY KEY,
  name varchar(32) DEFAULT NULL
);

-- Author table
DROP TABLE IF EXISTS author;

CREATE TABLE author (
  id int NOT NULL PRIMARY KEY,
  firstName varchar(32) NOT NULL,
  lastName varchar(32) DEFAULT NULL
);

-- Book table
DROP TABLE IF EXISTS book;

CREATE TABLE book (
  isbn varchar(13) NOT NULL PRIMARY KEY,
  title varchar(32) DEFAULT NULL,
  author_id int NOT NULL,
  pub_date date DEFAULT NULL,
  category_id int NOT NULL,
  stock int NOT NULL,
  price float NOT NULL,
  offer int DEFAULT NULL
);

alter table book add constraint category_fk foreign key (category_id) references category(id);
alter table book add constraint author_fk foreign key (author_id) references author(id);

-- Customer table
DROP TABLE IF EXISTS customer;

CREATE TABLE customer (
  id int IDENTITY NOT NULL PRIMARY KEY,
  firstname varchar(32) DEFAULT NULL,
  lastname varchar(32) DEFAULT NULL,
  address varchar(256) DEFAULT NULL,
  email varchar(32) NOT NULL,
  password varchar(32) DEFAULT NULL
);

ALTER TABLE customer ADD CONSTRAINT unique_email UNIQUE (email);

-- book order status table
DROP TABLE IF EXISTS book_order_status;

CREATE TABLE book_order_status (
  id int NOT NULL PRIMARY KEY,
  name varchar(32) DEFAULT NULL
);

-- Order table
DROP TABLE IF EXISTS book_order;

CREATE TABLE book_order (
  orderId int NOT NULL PRIMARY KEY,
  customerId int NOT NULL,
  orderDate date NOT NULL,
  statusId int NOT NULL
);

alter table book_order add constraint customer_fk foreign key (customerId) references customer(id);
alter table book_order add constraint status_fk foreign key (statusId) references book_order_status(id);

-- Order item table
DROP TABLE IF EXISTS book_order_item;

CREATE TABLE book_order_item (
  itemId int NOT NULL PRIMARY KEY,
  orderId int NOT NULL,
  bookId varchar(13) NOT NULL,
  quantity int NOT NULL
);

alter table book_order_item add constraint order_id_fk foreign key (orderId) references book_order(orderId);
alter table book_order_item add constraint book_id_fk foreign key (bookId) references book(isbn);