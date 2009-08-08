--UPDATE legal_time.sys_code
--SET   description = 'DB-0.0.0.3'
--WHERE code_id = 'DBVer';
ALTER TABLE `sys_code`
    MODIFY COLUMN `sys_code_id` INT(11) NOT NULL AUTO_INCREMENT;
--
ALTER TABLE `client`
    ADD COLUMN `bill_type` VARCHAR(25) NOT NULL DEFAULT 'HOURLY'
    AFTER `note`;
--
ALTER TABLE `client` ADD COLUMN `monthly_bill_rate` DOUBLE  AFTER `bill_type`;
--
ALTER TABLE `user_info` CHANGE COLUMN `defualt_bill_rate` `default_bill_rate` DOUBLE DEFAULT NULL;
--
insert into user_info (user_key, last_name, first_name, default_bill_rate)
    values('brian', 'Boger','Brian', 300);
--
INSERT INTO sys_code
    ( code_type, code_id, description, system_or_user, last_update) 
    VALUES ( 'VER', 'DBVer', 'DB-0.0.0.2', 'S', now());