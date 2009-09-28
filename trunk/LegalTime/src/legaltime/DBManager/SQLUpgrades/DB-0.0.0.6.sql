CREATE OR REPLACE VIEW vw_client_hourly_rate
(
	last_name
        , first_name
        , billing_person
        , bill_rate
) AS
select
    c.last_name
    , c.first_name
    , cbr.user_key
    , cbr.bill_rate
from
    client as c left join client_bill_rate as cbr
	on c.client_id = cbr.client_id
where
	active_yn ='Y'
	and bill_type='HOURLY'
order by
    c.last_name
    , c.first_name
    , cbr.bill_rate desc;
--
UPDATE sys_code
SET description = 'DB-0.0.0.6', last_update = now()
WHERE code_id = 'DBVer';