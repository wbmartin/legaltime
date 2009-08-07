ALTER TABLE `sys_code`
    MODIFY COLUMN `sys_code_id` INT(11) NOT NULL AUTO_INCREMENT;


ALTER TABLE `client`
    ADD COLUMN `bill_type` VARCHAR(25) NOT NULL DEFAULT 'HOURLY'
    AFTER `note`;

INSERT INTO sys_code
    ( code_type, code_id, description, system_or_user, last_update) 
    VALUES ( 'VER', 'DBVer', 'DB-0.0.0.2', 'S', now());

--UPDATE legal_time.sys_code
--SET   description = 'DB-0.0.0.3'
--WHERE code_id = 'DBVer';