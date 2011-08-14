DROP TABLE public.user_public;

CREATE TABLE public.user_public (
       client_id INTEGER NOT NULL
     , user_id TEXT NOT NULL
     , last_update TIMESTAMP(3)
     , last_name VARCHAR(25)
     , first_name VARCHAR(25)
     , middle_name VARCHAR(25)
     , office_address1 VARCHAR(25)
     , office_address2 VARCHAR(25)
     , office_city VARCHAR(25)
     , office_state CHAR(2)
     , office_zip CHAR(10)
     , title VARCHAR(25)
     , suffix VARCHAR(25)
     , office_phone CHAR(14)
     , fax CHAR(14)
     , office_cell CHAR(14)
     , comment TEXT
     , PRIMARY KEY (client_id, user_id)
     , CONSTRAINT FK_user_public_1 FOREIGN KEY (client_id, user_id)
                  REFERENCES public.security_user (client_id, user_id)
);

