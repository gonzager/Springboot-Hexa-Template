#
# Build stage
#
FROM maven:3.9.7-eclipse-temurin-21-alpine AS build
WORKDIR /app
COPY src ./src
COPY pom.xml .
RUN mvn -f pom.xml clean package

#
# Package stage
#

FROM openjdk:21
WORKDIR /app
COPY --from=build /app/target/template.jar .
EXPOSE 8080
ENTRYPOINT ["java","-jar","template.jar"]