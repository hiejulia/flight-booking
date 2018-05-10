#!/usr/bin/env bash
getPort() {
	echo $1 | cut -d ':' -f 3 | xargs basename
}

while ! `nc -z eurekaserver  $EUREKASERVER_PORT`; do sleep 3; done
while ! `nc -z database $DATABASESERVER_PORT`; do sleep 3; done
while ! `nc -z configserver $CONFIGSERVER_PORT `; do sleep 3; done


java -Djava.security.egd=file:/dev/./urandom -Dserver.port=$SERVER_PORT \
	 -Deureka.client.serviceUrl.defaultZone=$EUREKASERVER_URI \
	 -Dspring.cloud.config.uri=$CONFIGSERVER_URI \
     -Dspring.profiles.active=$PROFILE -jar /usr/local/licensingservice/@project.build.finalName@.jar