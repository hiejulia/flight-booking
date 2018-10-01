
CREATE KEYSPACE mykeyspace WITH REPLICATION = { 'class' : 'SimpleStrategy', 'replication_factor' : 1 };


USE mykeyspace;


drop table IF EXISTS mykeyspace.passenger;


CREATE TABLE mykeyspace.passenger (
    id text PRIMARY KEY,
    name text,
    age int
);


INSERT INTO mykeyspace.passenger (id,  name, age) VALUES ('625b36f0-1d6b-11e6-ac56-aba64e8c1447', 'Jane', 10);
INSERT INTO mykeyspace.passenger (id,  name, age) VALUES ('625b36f0-1d6b-11e6-ac56-aba64e8c1123', 'Jannie', 22);
INSERT INTO mykeyspace.passenger (id,  name, age)  VALUES ('625b36f0-1d6b-11e6-ac56-aba64e8c1447', 'Jann', 10);


create index on mykeyspace.passenger (name);

select * from mykeyspace.passenger where id='625b36f0-1d6b-11e6-ac56-aba64e8c1447';

select * from mykeyspace.passenger where name='Jane';