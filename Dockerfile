FROM openjdk:17

ARG FILE_JAR=demo/target/demo-0.0.1-SNAPSHOT.jar

ADD ${FILE_JAR} api-service2.jar

ENTRYPOINT ["java", "-jar", "api-service2.jar"]

EXPOSE 8088