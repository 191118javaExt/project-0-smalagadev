-- DROP TABLE IF EXISTS employee CASCADE;
-- DROP TABLE IF EXISTS customer CASCADE;

CREATE TABLE employee(
  emp_id SERIAL PRIMARY KEY,
  first_name VARCHAR(25) NOT NULL,
  last_name VARCHAR(25) NOT NULL,
  username VARCHAR(26) UNIQUE NOT NULL,
  password VARCHAR(15) NOT NULL,
  emp_role VARCHAR(20) NOT NULL
);

INSERT INTO employee(first_name, last_name, username, password, emp_role)
	VALUES('Aldous', 'Snow', 'ASnow', 'africanchild', 'Bank Admin');

INSERT INTO employee(first_name, last_name, username, password, emp_role)
	VALUES('John', 'McClain', 'JMcClain', 'yippeekayai', 'Employee');

INSERT INTO employee(first_name, last_name, username, password, emp_role)
	VALUES('John', 'Sparan', 'JSpartan', 'demolition', 'Employee');

INSERT INTO employee(first_name, last_name, username, password, emp_role)
	VALUES('John', 'Kimble', 'JKimble', 'kindergarten', 'Employee');


CREATE TABLE customer(
	customer_id SERIAL PRIMARY KEY,
	first_name VARCHAR(20) NOT NULL,
	last_name VARCHAR(20) NOT NULL,
	username VARCHAR(20) UNIQUE NOT NULL,
	password VARCHAR(16) NOT NULL,
	approved BOOLEAN,
	checking DECIMAL,
	savings DECIMAL
);

INSERT INTO customer(first_name, last_name, username, password, approved, checking, saving)
	VALUES('Simon', 'Phoenix', 'sphoenix', 'roundhouse', true, 5204.51, 38232.42);

INSERT INTO customer(first_name, last_name, username, password, approved, checking, saving)
	VALUES('Hans', 'Gruber', 'hgruber', 'nakatomi', true, 42114.87, 715922.44);

INSERT INTO customer(first_name, last_name, username, password, approved, checking, saving)
	VALUES('Cersei', 'Lannister', 'clannister', 'jamie4ever', true, 539040.90, 93103922.84);

INSERT INTO customer(first_name, last_name, username, password, approved, checking, saving)
	VALUES('Swiper', 'the Fox', 'sthefox', 'trynarobdora', true, 100.08, 57.42);

INSERT INTO customer(first_name, last_name, username, password, approved)
	VALUES('Simon', 'Gruber', 'sgruber', 'peterkrieg', false);

INSERT INTO customer(first_name, last_name, username, password, approved)
	VALUES('Parker', 'Wyndham', 'pwyndham', 'zoomzoomzoom', false);

INSERT INTO customer(first_name, last_name, username, password, approved)
	VALUES('Claude', 'Frollo', 'cfrollo', 'peterkrieg', false);

  INSERT INTO customer(first_name, last_name, username, password)
	VALUES('Tekashi', 'Sixnine', 'tsixnine', 'esttuuupit');

INSERT INTO customer(first_name, last_name, username, password)
	VALUES('Gucci''s Character', 'In Springbreakers', 'ginspringbreakers', 'beebeedubs');

INSERT INTO customer(first_name, last_name, username, password)
	VALUES('Emperor', 'Zurg', 'ezurg', 'buzzlightyear');
