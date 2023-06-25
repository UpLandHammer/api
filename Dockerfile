FROM openjdk:17
WORKDIR /app
ARG JAR_FILE=target/api-0.0.1-SNAPSHOT.jar
ADD $JAR_FILE /app/api-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT [ "java", "-jar", "/app/api-0.0.1-SNAPSHOT.jar" ]
