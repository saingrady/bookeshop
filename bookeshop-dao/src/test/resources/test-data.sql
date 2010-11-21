-- categories
INSERT INTO category VALUES (1, 'Java/JEE');
INSERT INTO category VALUES (2, 'Web Development');
INSERT INTO category VALUES (3, 'Unix/Linux');
INSERT INTO category VALUES (4, 'Network Administration');
INSERT INTO category VALUES (5, 'Database');
INSERT INTO category VALUES (6, 'Windows');

-- books
INSERT INTO book VALUES ('9781430216407', 'Pro Spring dm Server', 'Gary Mak', 2009, 39.99, 1);
INSERT INTO book VALUES ('9781430218418', 'Expert Shell scripting', 'Ron Peters', 2009, 39.99,3);
INSERT INTO book VALUES ('9781430218517', 'Cisco Routers', 'Jason Neumann ', 2009, 39.99,4);
INSERT INTO book VALUES ('9781430218890', 'Linux commnad line', 'Sander van Vugt', 2009, 34.99,3);
INSERT INTO book VALUES ('9781430219088', 'Pro Hyper-V', 'Harley Stagner', 2009, 44.99,6);
INSERT INTO book VALUES ('9781430224259', 'Silverlight 4 lab', 'Andrew Troelsen', 2009, 39.99,2);
INSERT INTO book VALUES ('9781430224693', 'Windows Azure', 'Henry Li ', 2009, 39.99,6);
INSERT INTO book VALUES ('9781430224976', 'Spring Entreprise recipes', 'Gary Mak', 2009, 44.99,1);
INSERT INTO book VALUES ('9781430225539', 'Java Google App Engine', 'Kyle Roche', 2009, 44.99,1);
INSERT INTO book VALUES ('9781430228028', 'Beginning Google Maps API 3', 'Gabriel Svennerberg ', 2010, 39.99,2);
INSERT INTO book VALUES ('9781430228509', 'Beginning Hibernate', 'Jeff Linwood', 2010, 39.99,1);
INSERT INTO book VALUES ('9781430228899', 'JavaEE6 with Glassfish', 'Antonio Goncalves', 2010, 39.99,1);
INSERT INTO book VALUES ('9781430232889', 'Pro CSS', 'Antony Kennedy', 2011, 49.99,2);
INSERT INTO book VALUES ('9781430233213', 'Windows Power Shell', 'Hristo Deshev', 2009, 39.99,6);
INSERT INTO book VALUES ('9781430268727', 'Java FX 2', 'Weiqi Gao', 2011, 39.99,1);
INSERT INTO book VALUES ('9781430272151', 'Adobe Cold Fusion', 'Michael Dinowitz', 2010, 39.99,2);
INSERT INTO book VALUES ('9781590592779', 'Samba 3', 'Richard Sharpe', 2003, 39.99,4);
INSERT INTO book VALUES ('9781590593578', 'WiMAx', 'Daniel Sweeney', 2005, 39.99,4);
INSERT INTO book VALUES ('9781590596746', 'Beginning Suse Linux', 'Keir Thomas', 2006, 39.99,3);
INSERT INTO book VALUES ('9781590597415', 'Expert MySql', 'Charles A. Bell', 2007, 29.99,5);
INSERT INTO book VALUES ('9781590598276', 'Pro Oracle Application', 'John Scott ', 2008, 49.99,5);
INSERT INTO book VALUES ('9781590599686', 'Oracle Database 11g', 'Darl Kuhn', 2010, 49.99,5);

-- book stock
INSERT INTO book_stock VALUES ('9781430216407',100);
INSERT INTO book_stock VALUES ('9781430218418',50);
INSERT INTO book_stock VALUES ('9781430218517',50);
INSERT INTO book_stock VALUES ('9781430218890',10);
INSERT INTO book_stock VALUES ('9781430219088',50);
INSERT INTO book_stock VALUES ('9781430224259',50);
INSERT INTO book_stock VALUES ('9781430224693',50);
INSERT INTO book_stock VALUES ('9781430224976',50);
INSERT INTO book_stock VALUES ('9781430225539',50);
INSERT INTO book_stock VALUES ('9781430228028',50);
INSERT INTO book_stock VALUES ('9781430228509',50);
INSERT INTO book_stock VALUES ('9781430228899',50);
INSERT INTO book_stock VALUES ('9781430232889',50);
INSERT INTO book_stock VALUES ('9781430233213',2);
INSERT INTO book_stock VALUES ('9781430268727',50);
INSERT INTO book_stock VALUES ('9781430272151',50);
INSERT INTO book_stock VALUES ('9781590592779',50);
INSERT INTO book_stock VALUES ('9781590593578',50);
INSERT INTO book_stock VALUES ('9781590596746',50);
INSERT INTO book_stock VALUES ('9781590597415',50);
INSERT INTO book_stock VALUES ('9781590598276',50);
INSERT INTO book_stock VALUES ('9781590599686',50);


-- book offers
INSERT INTO book_offer VALUES ('9781430216407',5);
INSERT INTO book_offer VALUES ('9781430218418',10);
INSERT INTO book_offer VALUES ('9781430218517',20);
INSERT INTO book_offer VALUES ('9781430218890',50);
INSERT INTO book_offer VALUES ('9781430219088',0);
INSERT INTO book_offer VALUES ('9781430224259',0);
INSERT INTO book_offer VALUES ('9781430224693',0);
INSERT INTO book_offer VALUES ('9781430224976',0);
INSERT INTO book_offer VALUES ('9781430225539',0);
INSERT INTO book_offer VALUES ('9781430228028',0);
INSERT INTO book_offer VALUES ('9781430228509',0);
INSERT INTO book_offer VALUES ('9781430228899',0);
INSERT INTO book_offer VALUES ('9781430232889',0);
INSERT INTO book_offer VALUES ('9781430233213',0);
INSERT INTO book_offer VALUES ('9781430268727',0);
INSERT INTO book_offer VALUES ('9781430272151',0);
INSERT INTO book_offer VALUES ('9781590592779',0);
INSERT INTO book_offer VALUES ('9781590593578',0);
INSERT INTO book_offer VALUES ('9781590596746',0);
INSERT INTO book_offer VALUES ('9781590597415',0);
INSERT INTO book_offer VALUES ('9781590598276',0);
INSERT INTO book_offer VALUES ('9781590599686',0);

-- customers
INSERT INTO customer VALUES ('md.benhassine@gmail.com', 'Mahmoud', 'Ben Hassine', 'somewhere on earth', 'mahmoud');
INSERT INTO customer VALUES ('mahmoud@server.com', 'Mahmood', 'Benassi', 'somewhere on moon', 'mahmood');