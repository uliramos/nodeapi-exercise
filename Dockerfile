FROM alpine:3.6
MAINTAINER uliramos@gmail.com
RUN apk add --no-cache openjdk8
COPY target/node-api-0.0.1-SNAPSHOT.jar /opt/springboot-app/node-api-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["/usr/bin/java"]
CMD ["-jar", "/opt/springboot-app/node-api-0.0.1-SNAPSHOT.jar"]