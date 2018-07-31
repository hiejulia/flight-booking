#!/usr/bin/env bash
docker run \
--name postgres \
--publish 5432:5432 \
--volume /opt/data/postgres:/var/lib/postgresql/data \
-e POSTGRES_USER=postgres \
-e POSTGRES_PASSWORD=postgres \
-e POSTGRES_DB=centric \
-d postgres