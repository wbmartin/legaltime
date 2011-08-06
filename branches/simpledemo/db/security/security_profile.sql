CREATE TABLE public.security_profile (
       client_id INTEGER NOT NULL
     , security_profile_id SERIAL NOT NULL
     , profile_name TEXT
     , last_update TIMESTAMP
     , PRIMARY KEY (client_id, security_profile_id)
     , CONSTRAINT fk_secuirtyprofile_client FOREIGN KEY (client_id)
                  REFERENCES public.client (client_id)
);

