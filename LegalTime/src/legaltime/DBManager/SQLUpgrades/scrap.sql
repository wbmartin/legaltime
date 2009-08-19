drop database legal_time; create database legal_time; use legal_time;

update labor_register set invoice_id = null;
delete from  client_account_register ;
delete from invoice ;
delete from labor_invoice_item ;
delete from labor_register where description ='Monthly Retainer Fee';