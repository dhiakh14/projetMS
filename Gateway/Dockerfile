FROM openjdk:17
EXPOSE 8090
ADD target/Gateway-1.0.jar ApiGateway.jar
ENTRYPOINT ["java", "-jar", "ApiGateway.jar"]