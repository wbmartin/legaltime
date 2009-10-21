alter table `client` add column `mortgage_pmt` Double after `monthly_bill_rate`;
--
UPDATE sys_code
SET description = 'DB-0.0.0.7', last_update = now()
WHERE code_id = 'DBVer';