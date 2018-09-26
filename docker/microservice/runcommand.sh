# build
mkdir build/

cp -r ../../code/* build/



# docker image 

docker build -t packt/microservice . --network=host

docker network create --subnet=172.10.0.0/16 packtNet

# run docker container


docker run -d --name redis --net packtNet --ip 172.10.0.40 redis

docker run -e "redis_ip=172.10.0.40" -e "eureka_registry=false" -d --net packtNet --ip 172.10.0.70 --name microservice packt/microservice

curl -v http://172.10.0.60:9090/