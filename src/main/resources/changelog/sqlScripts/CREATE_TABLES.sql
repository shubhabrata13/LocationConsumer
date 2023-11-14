CREATE SEQUENCE IF NOT EXISTS location_seq
    start 20000001
    increment 1
    NO MAXVALUE
    CACHE 1;

CREATE TABLE IF NOT EXISTS location_details
(
    locationPrimarykey VARCHAR(30) NOT NULL default nextval('location_seq'),
    name VARCHAR(30),
    location_id VARCHAR(30),
    status VARCHAR(30),
    bdaType VARCHAR(30),
    geoType VARCHAR(30),
    validTo VARCHAR(30),
    huluName VARCHAR(30),
    latitude VARCHAR(30),
    portFlag VARCHAR(30),
    timeZone VARCHAR(30),
    longitude VARCHAR(30),
    validFrom VARCHAR(30),
    restricted VARCHAR(30),
    description VARCHAR(30),
    dialingCode VARCHAR(30),
    isDuskCity VARCHAR(30),
    olsonTimezone VARCHAR(30),
    subCityParents VARCHAR(30),
    utcOffsetMinutes VARCHAR(30),
    workaroundReason VARCHAR(30),
    daylightSavingEnd VARCHAR(30),
    daylightSavingTime VARCHAR(30),
    daylightSavingStart VARCHAR(30),
    postalCodeMandatory VARCHAR(30),
    dialingCodeDescription VARCHAR(30),
    stateProvinceMandatory VARCHAR(30),
    daylightSavingShiftMinutes VARCHAR(30),
    CONSTRAINT location_pk PRIMARY KEY (locationPrimarykey)
);

ALTER SEQUENCE location_seq
    OWNED BY location_details.locationPrimarykey;

CREATE SEQUENCE IF NOT EXISTS bdas_seq
    start 20000001
    increment 1
    NO MAXVALUE
    CACHE 1;

CREATE TABLE IF NOT EXISTS bdas
(
    bdasPrimarykey VARCHAR(30) NOT NULL default nextval('bdas_seq'),
    name VARCHAR(30),
    type VARCHAR(30),
    bdaType VARCHAR(30),
    CONSTRAINT bdas_pk PRIMARY KEY (bdasPrimarykey)
);

ALTER SEQUENCE bdas_seq
    OWNED BY bdas.bdasPrimarykey;

