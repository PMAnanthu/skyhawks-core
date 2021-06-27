FROM openjdk:11
RUN mkdir logs
RUN chmod 777 logs
COPY build/libs/skyhawks-api-gateway.jar skyhawks-api-gateway.jar
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=prod","/skyhawks-api-gateway.jar"]
HEALTHCHECK CMD curl --fail http://localhost:8000/v0.1/actuator || exit 1