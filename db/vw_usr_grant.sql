CREATE OR REPLACE VIEW vw_user_grant AS 
 SELECT su.user_id, su.client_id, sp.profile_name, spriv.priv_name
   FROM security_user su
   LEFT JOIN security_profile sp ON su.client_id = sp.client_id AND su.security_profile_id = sp.security_profile_id
   LEFT JOIN security_profile_grant spg ON sp.client_id = spg.client_id AND sp.security_profile_id = spg.security_profile_id
   LEFT JOIN security_privilege spriv ON spg.client_id = spriv.client_id AND spg.security_privilege_id = spriv.security_privilege_id;

ALTER TABLE vw_user_grant OWNER TO postgres;
GRANT ALL ON TABLE vw_user_grant TO postgres;
GRANT ALL ON TABLE vw_user_grant TO simpledemo_grp;
