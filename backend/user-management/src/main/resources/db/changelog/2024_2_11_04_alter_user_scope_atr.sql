ALTER TABLE user_system
    ADD scope VARCHAR(10) NOT NULL CHECK (scope IN('USER','ORGANIZATION'));