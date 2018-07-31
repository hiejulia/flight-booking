#!/usr/bin/env bash

docker build -t agritsik/catalog-nginx docker/_images/catalog-nginx/

docker rm -f -v catalog-nginx
docker run -d -p 80:80 --link=catalog-gf:catalog-gf --name catalog-nginx agritsik/catalog-nginx

docker logs catalog-nginx