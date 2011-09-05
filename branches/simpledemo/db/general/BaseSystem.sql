DROP TABLE Public.sys_code;

CREATE TABLE Public.sys_code (
       sys_code_id SERIAL NOT NULL
     , code_type VARCHAR(5) NOT NULL
     , key VARCHAR(5)
     , value VARCHAR(25)
     , last_update TIMESTAMP(3)
     , notes VARCHAR(255)
     , PRIMARY KEY (sys_code_id)
);

