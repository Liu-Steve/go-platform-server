FROM eclipse-temurin:17
RUN apt-get update
RUN apt-get install -y zlib1g-dev libzip-dev
VOLUME /tmp
EXPOSE 8082
ARG JAR_FILE
ADD target/${JAR_FILE} app.jar
ADD target/classes/kata katago/src/main/resources/kata
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]