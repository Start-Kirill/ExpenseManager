\c expense_manager expense_manager;

CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

INSERT INTO app.limits(
	uuid, expense_category, limit_sum, currency_uuid, datetime_create)
	VALUES (uuid_generate_v4(), 'PRODUCT', 1000, (SELECT uuid FROM app.currencies WHERE currencies.name = 'USD'), CURRENT_TIMESTAMP),
	       (uuid_generate_v4(), 'SERVICE', 1000, (SELECT uuid FROM app.currencies WHERE currencies.name = 'USD'), CURRENT_TIMESTAMP);