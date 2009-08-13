--
alter table `expense_register` add column `invoiceable` BOOL after expense_register_id;
--
alter table `expense_register` add column `expense_date` DATE after `invoiceable`;
--
ALTER TABLE `expense_register` CHANGE COLUMN `amount` `amount` DOUBLE DEFAULT NULL;
--
UPDATE legal_time.sys_code
SET   description = 'DB-0.0.0.3', last_update = now()
WHERE code_id = 'DBVer';

