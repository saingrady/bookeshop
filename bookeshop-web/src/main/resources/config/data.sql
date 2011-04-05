-- categories
INSERT INTO category VALUES (1, 'Java/JEE');
INSERT INTO category VALUES (2, 'Web Development');
INSERT INTO category VALUES (3, 'Unix/Linux');
INSERT INTO category VALUES (4, 'Network Administration');
INSERT INTO category VALUES (5, 'Database');
INSERT INTO category VALUES (6, 'Windows');

-- authors
INSERT INTO author VALUES (1, 'Gary','Mak');
INSERT INTO author VALUES (2, 'Ron','Peters');
INSERT INTO author VALUES (3, 'Jason','Neumann');
INSERT INTO author VALUES (4, 'Sander','van Vugt');
INSERT INTO author VALUES (5, 'Harley','Stagner');
INSERT INTO author VALUES (6, 'Andrew','Troelsen');
INSERT INTO author VALUES (7, 'Henry','Li');
INSERT INTO author VALUES (8, 'Kyle','Roche');
INSERT INTO author VALUES (9, 'Gabriel','Svennerberg');
INSERT INTO author VALUES (10, 'Jeff','Linwood');
INSERT INTO author VALUES (11, 'Antonio','Goncalves');
INSERT INTO author VALUES (12, 'Antony','Kennedy');
INSERT INTO author VALUES (13, 'Hristo','Deshev');
INSERT INTO author VALUES (14, 'Weiqi','Gao');
INSERT INTO author VALUES (15, 'Michael','Deshev');
INSERT INTO author VALUES (16, 'Richard','Sharpe');
INSERT INTO author VALUES (17, 'Daniel','Sweeney');
INSERT INTO author VALUES (18, 'Keir','Thomas');
INSERT INTO author VALUES (19, 'Charles','A. Bell');
INSERT INTO author VALUES (20, 'John','Scott');
INSERT INTO author VALUES (21, 'Darl','Kuhn');

-- books
INSERT INTO book VALUES ('9781430216407', 'Pro Spring dm Server',1, '2009-05-25', 1,100,39.99,5);
INSERT INTO book VALUES ('9781430218418', 'Expert Shell scripting',2, '2009-01-06',3,50,39.99,10);
INSERT INTO book VALUES ('9781430218517', 'Cisco Routers',3, '2009-01-02',4,50,39.99,20);
INSERT INTO book VALUES ('9781430218890', 'Linux commnad line',4, '2009-04-22',3,10,34.99,50);
INSERT INTO book VALUES ('9781430219088', 'Pro Hyper-V',5, '2009-05-11',6,50,44.99,0);
INSERT INTO book VALUES ('9781430224259', 'Silverlight 4 lab',6, '2009-09-14',2,50,39.99,0);
INSERT INTO book VALUES ('9781430224693', 'Windows Azure',7, '2009-12-08',6,50,39.99,0);
INSERT INTO book VALUES ('9781430224976', 'Spring Entreprise recipes',1, '2009-11-20',1,50,44.99,0);
INSERT INTO book VALUES ('9781430225539', 'Java Google App Engine',8, '2009-12-31',1,50,44.99,0);
INSERT INTO book VALUES ('9781430228028', 'Beginning Google Maps API 3',9, '2010-07-27',2,50,39.99,0);
INSERT INTO book VALUES ('9781430228509', 'Beginning Hibernate',10, '2010-05-31',1,50,39.99,0);
INSERT INTO book VALUES ('9781430228899', 'JavaEE6 with Glassfish',11, '2010-08-31',1,50,44.99,0);
INSERT INTO book VALUES ('9781430232889', 'Pro CSS',12, '2011-04-20',2,50,39.99,0);
INSERT INTO book VALUES ('9781430233213', 'Windows Power Shell',13, '2011-01-25',6,2,39.99,0);
INSERT INTO book VALUES ('9781430268727', 'Java FX 2',14, '2010-01-11',1,50,39.99,0);
INSERT INTO book VALUES ('9781430272151', 'Adobe Cold Fusion',15, '2010-04-30',2,50,39.99,0);
INSERT INTO book VALUES ('9781590592779', 'The Definitive Guide to Samba 3',16, '2004-01-12',4,50,39.99,0);
INSERT INTO book VALUES ('9781590593578', 'WiMax Operators Manual',17, '2004-06-28',4,50,39.99,0);
INSERT INTO book VALUES ('9781590596746', 'Beginning Suse Linux',18, '2006-11-10',3,50,39.99,0);
INSERT INTO book VALUES ('9781590597415', 'Expert MySql',19, '2007-01-22',5,50,39.99,0);
INSERT INTO book VALUES ('9781590598276', 'Pro Oracle Application',20,'2008-09-19',5,50,29.99,0);
INSERT INTO book VALUES ('9781590599686', 'Oracle Database 11g',21, '2009-02-20',5,50,44.99,0);

-- customers
INSERT INTO customer VALUES (56325,'Mahmoud', 'Ben Hassine', 'somewhere on earth','md.benhassine@gmail.com', 'mahmoud');
INSERT INTO customer VALUES (56326,'Mahmood', 'Benassi', 'somewhere on moon', 'mahmood','mahmoud@server.com');

-- book order status
INSERT INTO book_order_status VALUES (1,'confirmed');
INSERT INTO book_order_status VALUES (2,'pending');
INSERT INTO book_order_status VALUES (3,'processed');
INSERT INTO book_order_status VALUES (4,'delivered');

-- book orders
INSERT INTO book_order VALUES (22135, 56325,'2010-02-05',4);
INSERT INTO book_order VALUES (22136, 56325,'2011-03-29',1);

-- book order items
INSERT INTO book_order_item VALUES (1,22135, '9781430218418',1);
INSERT INTO book_order_item VALUES (2,22136, '9781590599686',1);
INSERT INTO book_order_item VALUES (3,22136, '9781430228899',1);