DROP TABLE DEFAULT_SCHEMA.ledger_price;
DROP TABLE DEFAULT_SCHEMA.ledger_split;
DROP TABLE DEFAULT_SCHEMA.ledger_budget_amount;
DROP TABLE DEFAULT_SCHEMA.ledger_commodities;
DROP TABLE DEFAULT_SCHEMA.ledger_sched_transaction;
DROP TABLE DEFAULT_SCHEMA.ledger_budget;
DROP TABLE DEFAULT_SCHEMA.ledger_lot;
DROP TABLE DEFAULT_SCHEMA.ledger_transaction;
DROP TABLE DEFAULT_SCHEMA.ledger_accounts;

CREATE TABLE public.ledger_accounts (
       client_id INTEGER NOT NULL
     , ledger_account_id SERIAL NOT NULL
     , last_update TIMESTAMP(3) WITHOUT TIME ZONE NOT NULL
     , name VARCHAR(255) NOT NULL
     , account_type VARCHAR(25) NOT NULL
     , ledger_commodity_id INTEGER
     , parent_account_id INTEGER
     , code VARCHAR(255)
     , description VARCHAR(255)
     , PRIMARY KEY (client_id, ledger_account_id)
);

CREATE TABLE DEFAULT_SCHEMA.ledger_transaction (
       client_id INTEGER NOT NULL
     , ledger_transaction_id SERIAL NOT NULL
     , last_update TIMESTAMP(3) WITHOUT TIME ZONE
     , currency_id INTEGER
     , num INTEGER
     , post_date TIMESTAMP(3) WITHOUT TIME ZONE
     , enter_date TIMESTAMP(3) WITHOUT TIME ZONE
     , description VARCHAR(255)
     , PRIMARY KEY (client_id, ledger_transaction_id)
);

CREATE TABLE DEFAULT_SCHEMA.ledger_lot (
       client_id INTEGER NOT NULL
     , ledger_lot_id SERIAL NOT NULL
     , last_update TIMESTAMP(3) WITHOUT TIME ZONE
     , ledger_account_id INTEGER
     , is_closed INTEGER
     , PRIMARY KEY (client_id, ledger_lot_id)
);

CREATE TABLE DEFAULT_SCHEMA.ledger_budget (
       client_id INTEGER NOT NULL
     , ledger_budget_id SERIAL NOT NULL
     , last_update TIMESTAMP(3) WITHOUT TIME ZONE
     , name VARCHAR(25) NOT NULL
     , description VARCHAR(255)
     , PRIMARY KEY (client_id, ledger_budget_id)
);

CREATE TABLE DEFAULT_SCHEMA.ledger_sched_transaction (
       client_id INTEGER NOT NULL
     , ledger_schedule_transaction_id SERIAL NOT NULL
     , last_update TIMESTAMP(3) WITHOUT TIME ZONE
     , name VARCHAR(25)
     , start_date DATE
     , last_occur_date DATE
     , num_occur INTEGER
     , rem_occur INTEGER
     , auto_create BOOLEAN
     , auto_notify INTEGER
     , adv_creation_days INTEGER
     , adv_notify_days INTEGER
     , instance_count INTEGER
     , PRIMARY KEY (client_id, ledger_schedule_transaction_id)
);

CREATE TABLE DEFAULT_SCHEMA.ledger_commodities (
       client_id INTEGER NOT NULL
     , ledger_commodities_id SERIAL NOT NULL
     , last_update TIMESTAMP(3) WITHOUT TIME ZONE NOT NULL
     , mnemonic VARCHAR(25)
     , full_name VARCHAR(255)
     , cusip CHAR(10)
     , fraction INT
     , quote_flag CHAR(10)
     , quote_source VARCHAR(255)
     , quote_tz VARCHAR(255)
     , PRIMARY KEY (ledger_commodities_id, client_id)
);

CREATE TABLE DEFAULT_SCHEMA.ledger_budget_amount (
       client_id INTEGER NOT NULL
     , ledger_budget_amount_id SERIAL NOT NULL
     , last_update TIMESTAMP(3) WITHOUT TIME ZONE
     , ledger_budget_id INTEGER
     , ledger_account_id INTEGER
     , period_num INTEGER
     , amount_num INT8
     , amount_denom INT8
     , PRIMARY KEY (client_id, ledger_budget_amount_id)
     , CONSTRAINT FK_ledger_budget_amount_1 FOREIGN KEY (client_id, ledger_budget_id)
                  REFERENCES DEFAULT_SCHEMA.ledger_budget (client_id, ledger_budget_id)
     , CONSTRAINT FK_ledger_budget_amount_2 FOREIGN KEY (client_id, ledger_account_id)
                  REFERENCES DEFAULT_SCHEMA.ledger_accounts (client_id, ledger_account_id)
);

CREATE TABLE DEFAULT_SCHEMA.ledger_split (
       client_id INTEGER NOT NULL
     , ledger_split_id SERIAL NOT NULL
     , ledger_transaction_id INTEGER NOT NULL
     , ledger_account_id INTEGER NOT NULL
     , memo TEXT
     , action VARCHAR(255)
     , reconcile_state CHAR(1)
     , reconcile_date TIMESTAMP(3) WITHOUT TIME ZONE
     , value_num INTEGER NOT NULL
     , value_denom INTEGER NOT NULL
     , quantity_num INTEGER NOT NULL
     , quantity_denom CHAR(10) NOT NULL
     , ledger_lot_id CHAR(10)
     , PRIMARY KEY (client_id, ledger_split_id)
     , CONSTRAINT FK_ledger_split_1 FOREIGN KEY (client_id, ledger_transaction_id)
                  REFERENCES DEFAULT_SCHEMA.ledger_transaction (client_id, ledger_transaction_id) ON DELETE CASCADE
     , CONSTRAINT FK_ledger_split_2 FOREIGN KEY (client_id, ledger_account_id)
                  REFERENCES DEFAULT_SCHEMA.ledger_accounts (client_id, ledger_account_id)
);

CREATE TABLE DEFAULT_SCHEMA.ledger_price (
       client_id INTEGER NOT NULL
     , ledger_price_id SERIAL NOT NULL
     , last_update TIMESTAMP(3) WITHOUT TIME ZONE NOT NULL
     , ledger_commodity_id INTEGER NOT NULL
     , ledger_currency_id INTEGER NOT NULL
     , effective_date DATE NOT NULL
     , source VARCHAR(255)
     , type VARCHAR(255)
     , value_num INTEGER NOT NULL
     , value_denom INTEGER NOT NULL
     , ledger_commodities_id INTEGER NOT NULL
     , PRIMARY KEY (client_id, ledger_price_id)
     , CONSTRAINT FK_ledger_price_1 FOREIGN KEY (ledger_commodities_id, client_id)
                  REFERENCES DEFAULT_SCHEMA.ledger_commodities (ledger_commodities_id, client_id)
);

