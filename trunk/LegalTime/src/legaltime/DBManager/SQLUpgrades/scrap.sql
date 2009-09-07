drop database legal_time; create database legal_time; use legal_time;

update labor_register set invoice_id = null;
update payment_log set invoice_id = null;
update expense_register set invoice_id = null;
delete from  client_account_register ;
delete from invoice ;
delete from labor_invoice_item ;
delete from labor_register where description ='Monthly Retainer Fee';

GRANT ALL PRIVILEGES ON *.* TO 'root'@'%' IDENTIFIED BY '4vrf5btg';
FLUSH PRIVILEGES;

SELECT concat(first_name , ' ', + last_name) as client_name, followup.*
FROM followup left join client
on followup.client_id = client.client_id

