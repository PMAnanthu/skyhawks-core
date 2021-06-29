FROM openjdk:11
RUN mkdir logs
RUN chmod 777 logs
EXPOSE 8200
COPY build/libs/skyhawks-api-gateway-service-0.0.1.jar skyhawks-api-gateway-service.jar
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=prod","/skyhawks-api-gateway-service.jar"]
HEALTHCHECK CMD curl --fail http://localhost:8200/v0.1/actuator || exit 1