FROM openjdk:17-jdk-alpine
VOLUME /tmp
EXPOSE 8081
ADD go-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]