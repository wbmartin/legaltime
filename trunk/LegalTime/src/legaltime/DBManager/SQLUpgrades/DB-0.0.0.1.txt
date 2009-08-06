--
-- CREATE TABLE: client
--
CREATE TABLE IF NOT EXISTS client
(
	client_id INT NOT NULL AUTO_INCREMENT,
	first_name VARCHAR(50),
	last_name VARCHAR(50), 
	address VARCHAR(100),
	city VARCHAR(50),
	state VARCHAR(2),
	zip VARCHAR(10),
	work_phone VARCHAR(30),
	home_phone VARCHAR(30),
	cell_phone VARCHAR(30),
	fax VARCHAR(30),
	email VARCHAR(50),
	client_since_dt DATE,
	note TEXT,
	active_yn CHAR DEFAULT 'Y',
	last_update TIMESTAMP,
	CONSTRAINT PRIMARY KEY (client_id)
)
	ENGINE = InnoDB;
--
-- CREATE TABLE: invoice
--
CREATE TABLE IF NOT EXISTS invoice
(
	invoice_id INT NOT NULL AUTO_INCREMENT,
	invoice_dt DATE,
	generated_date DATE,
	invoice_total_amt DOUBLE,
	prev_balance_due DOUBLE,
	client_id INT NOT NULL,
	last_update TIMESTAMP,
	CONSTRAINT PRIMARY KEY (invoice_id)
)
	ENGINE = InnoDB;

--
-- CREATE FOREIGN KEY CONSTRAINT: fk_client_invoice
--
ALTER TABLE invoice ADD 
	CONSTRAINT fk_client_invoice FOREIGN KEY (client_id)
		REFERENCES client (client_id);

--
-- CREATE TABLE: followup
--
CREATE TABLE IF NOT EXISTS followup
(
	followup_id INT NOT NULL AUTO_INCREMENT,
	client_id INT NOT NULL,
	due_dt DATE,
	opened_date DATE,
	closed_dt DATE,
	description VARCHAR(20),
	last_update TIMESTAMP,
	CONSTRAINT PRIMARY KEY (followup_id, client_id)
)
	ENGINE = InnoDB;

--
-- CREATE FOREIGN KEY CONSTRAINT: fk_client_followup
--
ALTER TABLE followup ADD 
	CONSTRAINT fk_client_followup FOREIGN KEY (client_id)
		REFERENCES client (client_id);

--
-- CREATE TABLE: labor_invoice_item
--
CREATE TABLE IF NOT EXISTS labor_invoice_item
(
	labor_invoice_item_id INT NOT NULL AUTO_INCREMENT,
	activity_date DATE,
	activity_description VARCHAR(255),
	hours_billed DOUBLE,
	invoice_id INT NOT NULL,
	bill_rate DOUBLE,
	last_update TIMESTAMP,
	CONSTRAINT PRIMARY KEY (labor_invoice_item_id)
)
	ENGINE = InnoDB;
--
-- CREATE TABLE: expense_invoice_item
--
CREATE TABLE IF NOT EXISTS expense_invoice_item
(
	expense_invoice_item_id INT NOT NULL AUTO_INCREMENT,
	expense_date DATE,
	expense_description VARCHAR(255),
	hours_billed DOUBLE,
	invoice_id INT NOT NULL,
	last_update TIMESTAMP,
	CONSTRAINT PRIMARY KEY (expense_invoice_item_id)
)
	ENGINE = InnoDB;
--
-- CREATE TABLE: expense_register
--
CREATE TABLE IF NOT EXISTS expense_register
(
	expense_register_id INT NOT NULL AUTO_INCREMENT,
	description TEXT,
	amount VARCHAR(20),
	invoice_id INT,
	client_id INT NOT NULL,
	last_update TIMESTAMP,
	CONSTRAINT PRIMARY KEY (expense_register_id)
)
	ENGINE = InnoDB;

--
-- CREATE FOREIGN KEY CONSTRAINT: fk_invoice_expense_register
--
ALTER TABLE expense_register ADD 
	CONSTRAINT fk_invoice_expense_register FOREIGN KEY (invoice_id)
		REFERENCES invoice (invoice_id);

--
-- CREATE TABLE: user_info
--
CREATE TABLE IF NOT EXISTS user_info
(
	user_key VARCHAR(20) NOT NULL,
	last_name VARCHAR(20),
	first_name VARCHAR(20),
	defualt_bill_rate DOUBLE,
	last_update TIMESTAMP,
	CONSTRAINT PRIMARY KEY (user_key)
)
	ENGINE = InnoDB;
--
-- CREATE TABLE: labor_register
--
CREATE TABLE IF NOT EXISTS labor_register
(
	labor_register_id INT NOT NULL AUTO_INCREMENT,
	client_id INT NOT NULL,
	description TEXT,
	minutes INT,
	start_time DATETIME,
	end_time DATETIME,
	date DATE,
	invoice_id INT,
	user_key VARCHAR(20),
	last_update TIMESTAMP,
	CONSTRAINT PRIMARY KEY (labor_register_id, client_id)
)
	ENGINE = InnoDB;

--
-- CREATE FOREIGN KEY CONSTRAINT: fk_client_laborregister
--
ALTER TABLE labor_register ADD 
	CONSTRAINT fk_client_laborregister FOREIGN KEY (client_id)
		REFERENCES client (client_id);


--
-- CREATE FOREIGN KEY CONSTRAINT: fk_invoice_laborregister
--
ALTER TABLE labor_register ADD 
	CONSTRAINT fk_invoice_laborregister FOREIGN KEY (invoice_id)
		REFERENCES invoice (invoice_id);

--
-- CREATE TABLE: tran_type
--
CREATE TABLE IF NOT EXISTS tran_type
(
	tran_type_id INT NOT NULL AUTO_INCREMENT,
	tran_type VARCHAR(5) NOT NULL,
	description VARCHAR(20),
	last_update TIMESTAMP,
	CONSTRAINT PRIMARY KEY (tran_type_id, tran_type)
)
	ENGINE = InnoDB;
--
-- CREATE TABLE: client_account_register
--
CREATE TABLE IF NOT EXISTS client_account_register
(
	client_account_register_id INT NOT NULL AUTO_INCREMENT,
	client_id INT NOT NULL,
	tran_amt DOUBLE,
	tran_type VARCHAR(5),
	last_update TIMESTAMP,
	CONSTRAINT PRIMARY KEY (client_account_register_id, client_id)
)
	ENGINE = InnoDB;

--
-- CREATE FOREIGN KEY CONSTRAINT: Relation_1
--
ALTER TABLE client_account_register ADD 
	CONSTRAINT Relation_1 FOREIGN KEY (client_id)
		REFERENCES client (client_id);

--
-- CREATE TABLE: sys_code
--
CREATE TABLE IF NOT EXISTS sys_code
(
	sys_code_id INT NOT NULL,
	code_type VARCHAR(5),
	code_id VARCHAR(5),
	description VARCHAR(255),
	system_or_user VARCHAR(20),
	last_update TIMESTAMP,
	CONSTRAINT PRIMARY KEY (sys_code_id)
)
	ENGINE = InnoDB;
--
-- CREATE TABLE: client_bill_rate
--
CREATE TABLE IF NOT EXISTS client_bill_rate
(
	client_id INT NOT NULL,
	user_key VARCHAR(20) NOT NULL,
	bill_rate DOUBLE,
	last_update TIMESTAMP,
	CONSTRAINT PRIMARY KEY (client_id, user_key)
)
	ENGINE = InnoDB;

--
-- CREATE FOREIGN KEY CONSTRAINT: Relation_1
--
ALTER TABLE client_bill_rate ADD 
	FOREIGN KEY (user_key)
		REFERENCES user_info (user_key);



