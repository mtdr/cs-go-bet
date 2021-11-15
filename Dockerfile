FROM maven:3.8.3-jdk-11 AS builder
WORKDIR /home/app
COPY . ./
RUN mvn -f pom.xml clean package

FROM adoptopenjdk:11-jre-hotspot
ARG JAR_FILE=*.jar
COPY --from=builder /home/app/csgo-bet-server/target/${JAR_FILE} application.jar
EXPOSE 8085
ENTRYPOINT ["java", "-jar", "application.jar"]