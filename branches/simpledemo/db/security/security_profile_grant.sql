CREATE TABLE public.security_profile_grant (
       client_id INTEGER NOT NULL
     , security_privilege_id INTEGER NOT NULL
     , security_profile_id INTEGER NOT NULL
     , last_update TIMESTAMP(3) WITH TIME ZONE
     , PRIMARY KEY (client_id, security_privilege_id, security_profile_id)
     , CONSTRAINT FK_security_profile_grant_2 FOREIGN KEY (client_id, security_profile_id)
                  REFERENCES public.security_profile (client_id, security_profile_id)
     , CONSTRAINT fk_securityprofilegrant_securityprivilege FOREIGN KEY (client_id, security_privilege_id)
                  REFERENCES public.security_privilege (client_id, security_privilege_id)
);

