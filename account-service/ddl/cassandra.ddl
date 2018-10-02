CREATE KEYSPACE accounts WITH replication = {'class': 'SimpleStrategy', 'replication_factor': '1'}  AND durable_writes = true;

use accounts;

CREATE TABLE account(id uuid PRIMARY KEY, email text, password text, first_name text, last_name text, created timestamp);

CREATE INDEX ON account (email);
