CREATE SEQUENCE IF NOT EXISTS location_seq
    start 20000001
    increment 1
    NO MAXVALUE
    CACHE 1;

CREATE TABLE IF NOT EXISTS Location
(
    location_id VARCHAR(30) NOT NULL default nextval('location_seq'),
    source VARCHAR(4) NOT NULL,
    destination VARCHAR(4) NOT NULL,
    created_date TIMESTAMP,
    modified_date TIMESTAMP,
    modified_by varchar(10),
    created_by varchar(10),
    CONSTRAINT location_pk PRIMARY KEY (location_id)
);
