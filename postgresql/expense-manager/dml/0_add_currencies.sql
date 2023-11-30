\c expense_manager expense_manager;

CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

INSERT INTO app.currencies(
	uuid, name)
	VALUES (uuid_generate_v4(), 'USD'),
	       (uuid_generate_v4(), 'RUB'),
	       (uuid_generate_v4(), 'KZT');