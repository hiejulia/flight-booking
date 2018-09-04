FROM openjdk:8u151-jdk-alpine
LABEL maintainer="Hien Nguyen"
EXPOSE 8080
RUN set -ex \
  && apk update \
  && apk upgrade \
  && apk add git
RUN mkdir /billing-service \
  && git clone --depth 1 --branch build-artifacts-gke \
      "https://github.com/hiennguyen/billing-service.git" /billing-service \
  && cd /billing-service \
  && mv billing-service-*.jar billing-service.jar
CMD [ "java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "billing/billing-service.jar" ]