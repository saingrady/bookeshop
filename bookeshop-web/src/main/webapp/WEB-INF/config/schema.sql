DROP TABLE IF EXISTS customer;

CREATE TABLE customer (
  email varchar(32) NOT NULL PRIMARY KEY,
  firstname varchar(32) DEFAULT NULL,
  lastname varchar(32) DEFAULT NULL,
  address varchar(256) DEFAULT NULL,
  password varchar(32) DEFAULT NULL
);

DROP TABLE IF EXISTS category;

CREATE TABLE category (
  id int NOT NULL PRIMARY KEY,
  name varchar(32) DEFAULT NULL
);

DROP TABLE IF EXISTS book;

CREATE TABLE book (
  isbn varchar(13) NOT NULL PRIMARY KEY,
  title varchar(32) DEFAULT NULL,
  author varchar(32) DEFAULT NULL,
  year int DEFAULT NULL,
  price float DEFAULT NULL,
  quantity int DEFAULT NULL,
  category_id int NOT NULL
);

alter table book add constraint book_fk foreign key (category_id) references category(id);