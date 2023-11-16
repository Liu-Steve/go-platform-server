FROM openjdk:17-jdk-alpine
VOLUME /tmp
#EXPOSE 8084
#EXPOSE 8280
ADD go-server-*.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:8280","-jar","/app.jar"]