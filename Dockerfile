FROM maven:3.6.3-jdk-11-slim AS build
VOLUME /tmp
COPY . ./
RUN mvn clean package

FROM openjdk:11
VOLUME /tmp
EXPOSE 8081
ARG JAR_FILE=target/orchestrator-0.0.1-SNAPSHOT.jar
COPY --from=build /${JAR_FILE} ./app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]