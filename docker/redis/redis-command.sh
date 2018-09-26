docker network create --subnet=172.10.0.0/16 packet

# run the container 
docker run -d --name redis --net packtNet --ip 172.10.0.40 redis 