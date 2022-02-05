FROM openjdk:11 AS build
COPY pom.xml mvnw ./
COPY .mvn .mvn
COPY src src
FROM openjdk:11
WORKDIR AccountApi
COPY  target/*.jar account-api.jar
ENTRYPOINT ["java", "-jar", "account-api.jar"]