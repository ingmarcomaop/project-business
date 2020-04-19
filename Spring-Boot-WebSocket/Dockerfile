FROM openjdk:8-jdk-alpine

EXPOSE 80
VOLUME /tmp

WORKDIR /spring-boot-websocket

COPY ./target/spring-boot-websocket-0.0.1-SNAPSHOT.jar ./app.jar

ENTRYPOINT ["java","-jar","/spring-boot-websocket/app.jar"]