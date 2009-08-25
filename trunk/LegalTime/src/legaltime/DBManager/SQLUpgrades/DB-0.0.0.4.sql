--
ALTER TABLE `client_account_register` DROP PRIMARY KEY,
 ADD PRIMARY KEY  USING BTREE(`client_account_register_id`);
--
alter table `client_account_register` add column `effective_date` DATE after `client_id`;
--
update client_account_register set effective_date = last_update;
--
ALTER TABLE payment_log ADD COLUMN client_account_register_id INTEGER(11)  NOT NULL AFTER `client_id`;
--
ALTER TABLE payment_log ADD
	CONSTRAINT fk_clientaccountregister_paymentlog FOREIGN KEY (client_account_register_id)
		REFERENCES client_account_register (client_account_register_id);
--
alter table followup change column description description varchar(255) default null;
--
ALTER TABLE `followup` DROP PRIMARY KEY,
 ADD PRIMARY KEY  USING BTREE(`followup_id`);
--
UPDATE sys_code
SET description = 'DB-0.0.0.4', last_update = now()
WHERE code_id = 'DBVer';