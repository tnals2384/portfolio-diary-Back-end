FROM openjdk:11-jdk

RUN apt-get update && apt-get -y install sudo

ARG JAR_FILE="build/libs/simple-*.jar"

COPY ${JAR_FILE} app.jar

ENV    PROFILE local

ENTRYPOINT ["java", "-Dspring.profiles.active=${PROFILE}", "-jar","/app.jar"]