ALTER TABLE IF EXISTS app.currency_rates
    ADD COLUMN dt_create time without time zone NOT NULL;

ALTER TABLE IF EXISTS app.currency_rates
    ADD COLUMN dt_update time without time zone NOT NULL;

ALTER TABLE IF EXISTS app.currency_rates
    ADD COLUMN status text NOT NULL;

ALTER TABLE IF EXISTS app.currency_rates
    ADD COLUMN attempt bigint NOT NULL;