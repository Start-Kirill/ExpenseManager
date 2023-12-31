DO
$do$
BEGIN
   IF EXISTS (
      SELECT FROM pg_catalog.pg_roles
      WHERE  rolname = 'expense_manager') THEN

      RAISE NOTICE 'Role "expense_manager" already exists. Skipping.';
   ELSE
      CREATE ROLE expense_manager LOGIN PASSWORD 'q1w2e3r4';
   END IF;
END
$do$;

\c expense_manager expense_manager;

DO
$do$
BEGIN
   IF EXISTS (
      SELECT FROM information_schema.schemata
      WHERE  schema_name = 'app') THEN

      RAISE NOTICE 'Schema "app" already exists. Skipping.';
   ELSE
      CREATE SCHEMA app AUTHORIZATION expense_manager;
   END IF;
END
$do$;

CREATE TABLE app.currencies
(
    uuid uuid,
    name text NOT NULL,
    PRIMARY KEY (uuid),
    UNIQUE (name)
);

ALTER TABLE IF EXISTS app.currencies
    OWNER to expense_manager;

CREATE TABLE app.currency_rates
(
    uuid uuid,
    first_currency_uuid uuid NOT NULL,
    second_currency_uuid uuid NOT NULL,
    value numeric,
    datetime_create timestamp without time zone NOT NULL,
    datetime_create timestamp without time zone NOT NULL,
    status text NOT NULL,
    attempt bigint NOT NULL,
    PRIMARY KEY (uuid),
    FOREIGN KEY (first_currency_uuid)
        REFERENCES app.currencies (uuid) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    FOREIGN KEY (second_currency_uuid)
        REFERENCES app.currencies (uuid) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
);

ALTER TABLE IF EXISTS app.currency_rates
    OWNER to expense_manager;

CREATE TABLE app.limits
(
    uuid uuid,
    expense_category text NOT NULL,
    limit_sum numeric NOT NULL,
    currency_uuid uuid NOT NULL,
    datetime_create timestamp without time zone NOT NULL,
    PRIMARY KEY (uuid),
    FOREIGN KEY (currency_uuid)
        REFERENCES app.currencies (uuid) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
);

ALTER TABLE IF EXISTS app.limits
    OWNER to expense_manager;

CREATE TABLE app.transactions
(
    uuid uuid,
    currency_uuid uuid NOT NULL,
    expense_category text NOT NULL,
    account_from text NOT NULL,
    account_to text NOT NULL,
    trans_sum numeric NOT NULL,
    trans_sum_in_usd numeric NOT NULL,
    limit_uuid uuid NOT NULL,
    exceeded boolean NOT NULL,
    datetime timestamp without time zone NOT NULL,
    PRIMARY KEY (uuid),
    FOREIGN KEY (currency_uuid)
        REFERENCES app.currencies (uuid) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    FOREIGN KEY (limit_uuid)
        REFERENCES app.limits (uuid) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
);

ALTER TABLE IF EXISTS app.transactions
    OWNER to expense_manager;