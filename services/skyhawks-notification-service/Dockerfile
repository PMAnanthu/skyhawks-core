FROM openjdk:11
RUN mkdir logs
RUN chmod 777 logs
COPY build/libs/skyhawks-notification-service.jar skyhawks-notification-service.jar
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=prod","/skyhawks-notification-service.jar"]
HEALTHCHECK CMD curl --fail http://localhost:9000/v0.1/actuator || exit 1