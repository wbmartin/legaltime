--
-- CREATE VIEW: vw_client_followup
--
CREATE OR REPLACE VIEW vw_client_followup
(
	followup_id,
	due_dt,
	opened_date,
	closed_dt,
	description,
	first_name,
	last_name
) AS
SELECT
	followup.followup_id,
	followup.due_dt,
	followup.opened_date,
	followup.closed_dt,
	followup.description,
	client.first_name,
	client.last_name
FROM
	followup left join client
          on followup.client_id = client.client_id
WHERE	closed_dt is null
ORDER BY	due_dt;
--
UPDATE sys_code
SET description = 'DB-0.0.0.5', last_update = now()
WHERE code_id = 'DBVer';