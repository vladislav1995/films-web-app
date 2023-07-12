CREATE SCHEMA IF NOT EXISTS apollo_federation;

SET search_path TO apollo_federation;

CREATE TABLE IF NOT EXISTS apollo_federation.film(
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);