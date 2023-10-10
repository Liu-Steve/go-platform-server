FROM openjdk:17-jdk-alpine
VOLUME /tmp
EXPOSE 8084
ADD go-server-*.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]