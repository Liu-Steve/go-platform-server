FROM openjdk:17-jdk-alpine
RUN apk add --update --no-cache libzip-dev
VOLUME /tmp
EXPOSE 8083
ARG JAR_FILE
ARG JAR_VERSION
LABEL version=${JAR_VERSION}
LABEL latest
ADD target/${JAR_FILE} app.jar
ADD target/classes/kata katago/src/main/resources/kata
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]