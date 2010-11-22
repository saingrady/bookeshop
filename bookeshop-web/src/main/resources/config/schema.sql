-- Customer table
DROP TABLE IF EXISTS customer;

CREATE TABLE customer (
  id int IDENTITY NOT NULL PRIMARY KEY,
  email varchar(32) NOT NULL,
  firstname varchar(32) DEFAULT NULL,
  lastname varchar(32) DEFAULT NULL,
  address varchar(256) DEFAULT NULL,
  password varchar(32) DEFAULT NULL
);

ALTER TABLE customer ADD CONSTRAINT unique_email UNIQUE (email);

-- Category table
DROP TABLE IF EXISTS category;

CREATE TABLE category (
  id int NOT NULL PRIMARY KEY,
  name varchar(32) DEFAULT NULL
);

-- Book table
DROP TABLE IF EXISTS book;

CREATE TABLE book (
  isbn varchar(13) NOT NULL PRIMARY KEY,
  title varchar(32) DEFAULT NULL,
  author varchar(32) DEFAULT NULL,
  year int DEFAULT NULL,
  price float DEFAULT NULL,
  category_id int NOT NULL
);

alter table book add constraint book_fk foreign key (category_id) references category(id);

-- Book stock table
-- TODO add trigger for negative stock
DROP TABLE IF EXISTS book_stock;

CREATE TABLE book_stock (
  bookId varchar(13) NOT NULL PRIMARY KEY,
  stock int DEFAULT NULL
);

alter table book_stock add constraint book_stock_fk foreign key (bookId) references book(isbn);

-- Book offer table
DROP TABLE IF EXISTS book_offer;

CREATE TABLE book_offer (
  bookId varchar(13) NOT NULL PRIMARY KEY,
  offer int DEFAULT NULL
);

alter table book_offer add constraint book_offer_fk foreign key (bookId) references book(isbn);

