# FROM adoptopenjdk:11-jre-hotspot
# ARG JAR_FILE=*.jar
# COPY ${JAR_FILE} application.jar
# ENTRYPOINT ["java", "-jar", "application.jar"]

#
# Build stage
#

FROM maven:3.6.0-jdk-11-slim AS builder
WORKDIR /opt/cs-go-bet
COPY ../ /.
RUN mvn -f ./pom.xml clean package && ls


#
# Package stage
#
# FROM openjdk:11-jre-slim
# COPY --from=build /home/app/target/demo-0.0.1-SNAPSHOT.jar /usr/local/lib/demo.jar
# EXPOSE 8080
# ENTRYPOINT ["java","-jar","/usr/local/lib/demo.jar"]

FROM adoptopenjdk:11-jre-hotspot
ARG JAR_FILE=*.jar
COPY --from=builder /opt/cs-go-bet/_contributing/csgo-bet-server/target/${JAR_FILE} application.jar
EXPOSE 8085
ENTRYPOINT ["java", "-jar", "application.jar"]