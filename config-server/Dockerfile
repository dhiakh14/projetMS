FROM openjdk:17
EXPOSE 8081
ADD target/config-server-1.0.jar ConfigServer.jar
ENTRYPOINT ["java", "-jar", "ConfigServer.jar"]