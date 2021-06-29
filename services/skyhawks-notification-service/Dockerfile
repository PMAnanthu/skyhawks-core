FROM openjdk:11
RUN mkdir logs
RUN chmod 777 logs
EXPOSE 8050
EXPOSE 587/tcp
COPY build/libs/skyhawks-notification-service-0.0.1.jar skyhawks-notification-service.jar
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=prod","/skyhawks-notification-service.jar"]
HEALTHCHECK CMD curl --fail http://localhost:8050/v0.1/actuator || exit 1