#!/bin/bash
 

auth="-u user -p pwd"

# MONGODB USER CREATION
(
echo "setup mongodb auth"
create_user="if (!db.getUser('user')) { db.createUser({ user: 'user', pwd: 'pwd', roles: [ {role:'readWrite', db:'geocodageDatabase'} ]}) }"
until mongo geocodageDatabase --eval "$create_user" || mongo geocodageDatabase $auth --eval "$create_user"; do sleep 5; done
killall mongod
sleep 1
killall -9 mongod
) &

# INIT DUMP EXECUTION
(
if test -n "$INIT_DUMP"; then
    echo "execute dump file"
	until mongo geocodageDatabase $auth $INIT_DUMP; do sleep 5; done
fi
) &

echo "start mongodb without auth"
chown -R mongodb /data/db
gosu mongodb mongod "$@"

echo "restarting with auth on"
sleep 5
exec gosu mongodb mongod --auth "$@"

