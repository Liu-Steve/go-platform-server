FROM openjdk:17-jdk-alpine
VOLUME /tmp
EXPOSE 8084
ADD katago-*.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]