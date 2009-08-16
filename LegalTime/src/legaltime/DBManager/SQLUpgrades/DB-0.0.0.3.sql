--
alter table `expense_register` add column `invoiceable` BOOL after expense_register_id;
--
alter table `expense_register` add column `expense_date` DATE after `invoiceable`;
--
ALTER TABLE `expense_register` CHANGE COLUMN `amount` `amount` DOUBLE DEFAULT NULL;
--
--
-- CREATE TABLE: payment_log
--
CREATE TABLE IF NOT EXISTS payment_log
(
	payment_log_id INT NOT NULL,
	effective_date DATE,
	description VARCHAR(255),
	amount DOUBLE,
	invoice_id INT,
	client_id INT NOT NULL,
	CONSTRAINT PRIMARY KEY (payment_log_id)
)
	ENGINE = InnoDB;

--
-- CREATE FOREIGN KEY CONSTRAINT: fk_invoice_paymentlog
--
ALTER TABLE payment_log ADD
	CONSTRAINT fk_invoice_paymentlog FOREIGN KEY (invoice_id)
		REFERENCES invoice (invoice_id);


--
-- CREATE FOREIGN KEY CONSTRAINT: fk_client_paymentlog
--
ALTER TABLE payment_log ADD
	CONSTRAINT fk_client_paymentlog FOREIGN KEY (client_id)
		REFERENCES client (client_id);
--
UPDATE legal_time.sys_code
SET   description = 'DB-0.0.0.3', last_update = now()
WHERE code_id = 'DBVer';

