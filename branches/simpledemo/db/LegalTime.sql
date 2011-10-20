DROP TABLE public.sys_code;
DROP TABLE public.customer_additional_info;
DROP TABLE public.followup;
DROP TABLE public.customer_bill_rate;
DROP TABLE public.labor_invoice_item;
DROP TABLE public.payment_log;
DROP TABLE public.expense_invoice_item;
DROP TABLE public.labor_register;
DROP TABLE public.expense_register;
DROP TABLE public.invoice;
DROP TABLE public.customer_account_register;
DROP TABLE public.customer;
DROP TABLE public.client_pref;
DROP TABLE public.user_info;
DROP TABLE public.tran_type;

CREATE TABLE public.tran_type (
       client_id INTEGER NOT NULL
     , tran_type VARCHAR(5) NOT NULL
     , last_update TIMESTAMP(3) WITHOUT TIME ZONE DEFAULT now()
     , description VARCHAR(20)
     , PRIMARY KEY (client_id, tran_type)
);

CREATE TABLE public.user_info (
       user_id TEXT NOT NULL
     , client_id INTEGER NOT NULL
     , last_update TIMESTAMP(3) WITHOUT TIME ZONE DEFAULT now()
     , default_bill_rate DOUBLE PRECISION
     , email_addr VARCHAR(255)
     , display_name VARCHAR(25)
     , PRIMARY KEY (user_id, client_id)
);

CREATE TABLE public.client_pref (
       client_id SERIAL NOT NULL
     , last_update TIMESTAMP(3) WITHOUT TIME ZONE
     , client_name TEXT
     , PRIMARY KEY (client_id)
);

CREATE TABLE public.customer (
       customer_id SERIAL NOT NULL
     , client_id INTEGER NOT NULL
     , last_update TIMESTAMP(3) WITHOUT TIME ZONE
     , first_name VARCHAR(50)
     , last_name VARCHAR(50)
     , address TEXT
     , city VARCHAR(50)
     , state CHAR(2)
     , zip VARCHAR(10)
     , work_phone VARCHAR(30)
     , home_phone VARCHAR(30)
     , fax VARCHAR(30)
     , email VARCHAR(100)
     , client_since_dt DATE
     , note TEXT
     , bill_type VARCHAR(25)
     , monthly_bill_rate DOUBLE PRECISION
     , active_yn CHAR(1)
     , mortgage_amount DOUBLE PRECISION
     , contingency_rate DOUBLE PRECISION
     , suspend_invoice CHAR(1)
     , PRIMARY KEY (customer_id, client_id)
     , CONSTRAINT FK_customer_1 FOREIGN KEY (client_id)
                  REFERENCES public.client_pref (client_id)
);

CREATE TABLE public.customer_account_register (
       customer_account_register_id SERIAL NOT NULL
     , client_id INTEGER NOT NULL
     , customer_id INTEGER NOT NULL
     , last_update TIMESTAMP(3) WITHOUT TIME ZONE DEFAULT now()
     , effective_dt DATE
     , description TEXT
     , tran_amt DOUBLE PRECISION
     , tran_type VARCHAR(5)
     , ref_id INTEGER
     , PRIMARY KEY (customer_account_register_id, client_id, customer_id)
     , CONSTRAINT FK_client_account_register_2 FOREIGN KEY (customer_id, client_id)
                  REFERENCES public.customer (customer_id, client_id)
     , CONSTRAINT FK_client_account_register_3 FOREIGN KEY (client_id, tran_type)
                  REFERENCES public.tran_type (client_id, tran_type)
);

CREATE TABLE public.invoice (
       invoice_id SERIAL NOT NULL
     , client_id INTEGER NOT NULL
     , customer_id INTEGER NOT NULL
     , last_update TIMESTAMP(3) WITHOUT TIME ZONE
     , invoice_dt DATE
     , generated_dt DATE
     , invoice_total_amt DOUBLE PRECISION
     , prev_balance_due DOUBLE PRECISION
     , PRIMARY KEY (invoice_id, client_id, customer_id)
     , CONSTRAINT FK_invoice_1 FOREIGN KEY (customer_id, client_id)
                  REFERENCES public.customer (customer_id, client_id)
);

CREATE TABLE public.expense_register (
       expense_register_id SERIAL NOT NULL
     , client_id INTEGER NOT NULL
     , customer_id INTEGER NOT NULL
     , last_update TIMESTAMP(3) WITHOUT TIME ZONE DEFAULT now()
     , description TEXT
     , amount DOUBLE PRECISION
     , invoice_id INTEGER
     , invoiceable BOOL
     , expense_dt DATE
     , PRIMARY KEY (expense_register_id, client_id, customer_id)
     , CONSTRAINT FK_expense_register_1 FOREIGN KEY (customer_id, client_id)
                  REFERENCES public.customer (customer_id, client_id)
);

CREATE TABLE public.labor_register (
       labor_register_id SERIAL NOT NULL
     , client_id INTEGER NOT NULL
     , customer_id INTEGER NOT NULL
     , last_update TIMESTAMP(3) WITHOUT TIME ZONE DEFAULT now()
     , description TEXT
     , minute_count INTEGER
     , start_time TIMESTAMP
     , end_time TIMESTAMP
     , activity_date DATE
     , invoiceable BOOL
     , bill_rate DOUBLE PRECISION
     , invoice_id INTEGER
     , user_id TEXT
     , PRIMARY KEY (labor_register_id, client_id, customer_id)
     , CONSTRAINT FK_labor_register_1 FOREIGN KEY (customer_id, client_id)
                  REFERENCES public.customer (customer_id, client_id)
     , CONSTRAINT FK_labor_register_2 FOREIGN KEY (user_id, client_id)
                  REFERENCES public.user_info (user_id, client_id)
     , CONSTRAINT FK_labor_register_3 FOREIGN KEY (invoice_id, client_id, customer_id)
                  REFERENCES public.invoice (invoice_id, client_id, customer_id)
);

CREATE TABLE public.expense_invoice_item (
       expense_invoice_item_id SERIAL NOT NULL
     , client_id INTEGER NOT NULL
     , customer_id INTEGER NOT NULL
     , last_update TIMESTAMP(3) WITHOUT TIME ZONE
     , expense_dt DATE
     , expense_description TEXT
     , amount DOUBLE PRECISION
     , invoice_id INTEGER
     , PRIMARY KEY (expense_invoice_item_id, client_id, customer_id)
     , CONSTRAINT FK_expense_invoice_item_1 FOREIGN KEY (customer_id, client_id)
                  REFERENCES public.customer (customer_id, client_id)
     , CONSTRAINT FK_expense_invoice_item_2 FOREIGN KEY (invoice_id, client_id, customer_id)
                  REFERENCES public.invoice (invoice_id, client_id, customer_id)
);

CREATE TABLE public.payment_log (
       payment_log_id SERIAL NOT NULL
     , client_id INTEGER NOT NULL
     , customer_id INTEGER NOT NULL
     , last_update TIMESTAMP(3) WITHOUT TIME ZONE
     , effective_dt DATE
     , description TEXT
     , amount DOUBLE PRECISION
     , invoice_id INTEGER
     , customer_account_register_id INTEGER
     , PRIMARY KEY (payment_log_id, client_id, customer_id)
     , CONSTRAINT FK_payment_log_1 FOREIGN KEY (customer_id, client_id)
                  REFERENCES public.customer (customer_id, client_id)
     , CONSTRAINT FK_payment_log_3 FOREIGN KEY (invoice_id, client_id, customer_id)
                  REFERENCES public.invoice (invoice_id, client_id, customer_id)
     , CONSTRAINT FK_paymentlog_customeraccountregister FOREIGN KEY (customer_account_register_id, client_id, customer_id)
                  REFERENCES public.customer_account_register (customer_account_register_id, client_id, customer_id)
);

CREATE TABLE public.labor_invoice_item (
       labor_invoice_item_id SERIAL NOT NULL
     , client_id INTEGER NOT NULL
     , customer_id INTEGER NOT NULL
     , last_update TIMESTAMP(3) WITHOUT TIME ZONE
     , activity_dt DATE
     , activity_description TEXT
     , user_id TEXT
     , invoice_id INTEGER NOT NULL
     , hours_billed DOUBLE PRECISION
     , bill_rate DOUBLE PRECISION
     , PRIMARY KEY (labor_invoice_item_id, client_id, customer_id)
     , CONSTRAINT FK_laborinvoiceitem_customer FOREIGN KEY (customer_id, client_id)
                  REFERENCES public.customer (customer_id, client_id)
     , CONSTRAINT FK_laborinvoiceitem_invoice FOREIGN KEY (invoice_id, client_id, customer_id)
                  REFERENCES public.invoice (invoice_id, client_id, customer_id)
);

CREATE TABLE public.customer_bill_rate (
       customer_bill_rate_id SERIAL NOT NULL
     , client_id INTEGER NOT NULL
     , last_update TIMESTAMP(3) WITHOUT TIME ZONE
     , customer_id INTEGER
     , user_id TEXT
     , bill_rate DOUBLE PRECISION
     , PRIMARY KEY (customer_bill_rate_id, client_id)
     , CONSTRAINT FK_customer_bill_rate_1 FOREIGN KEY (customer_id, client_id)
                  REFERENCES public.customer (customer_id, client_id)
     , CONSTRAINT FK_customer_bill_rate_2 FOREIGN KEY (user_id, client_id)
                  REFERENCES public.user_info (user_id, client_id)
);

CREATE TABLE public.followup (
       followup_id SERIAL NOT NULL
     , client_id INTEGER NOT NULL
     , last_update TIMESTAMP(3) WITHOUT TIME ZONE
     , customer_id INTEGER
     , due_dt DATE
     , open_dt DATE
     , close_dt DATE
     , followup_description TEXT
     , assigned_user_id TEXT
     , PRIMARY KEY (followup_id, client_id)
     , CONSTRAINT FK_followup_1 FOREIGN KEY (customer_id, client_id)
                  REFERENCES public.customer (customer_id, client_id)
);

CREATE TABLE public.customer_additional_info (
       client_id INTEGER
     , customer_id INTEGER
     , last_update TIMESTAMP(3)
     , sys_code_id INTEGER
     , value VARCHAR(255)
     , CONSTRAINT FK_customer_additional_info_1 FOREIGN KEY (customer_id, client_id)
                  REFERENCES public.customer (customer_id, client_id)
);

CREATE TABLE public.sys_code (
       sys_code_id SERIAL NOT NULL
     , client_id INTEGER NOT NULL
     , last_update TIMESTAMP(3) WITHOUT TIME ZONE
     , code_type VARCHAR(5)
     , code_id VARCHAR(5)
     , description VARCHAR(255)
     , system_or_user VARCHAR(20)
     , PRIMARY KEY (sys_code_id, client_id)
     , CONSTRAINT FK_syscode_client FOREIGN KEY (client_id)
                  REFERENCES public.client_pref (client_id)
);

