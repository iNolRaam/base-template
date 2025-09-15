DROP SCHEMA IF EXISTS base_template CASCADE;
DROP ROLE IF EXISTS postgres;
CREATE SCHEMA base_template;
CREATE ROLE postgres WITH PASSWORD 'postgres' SUPERUSER;
ALTER SCHEMA base_template OWNER TO postgres;