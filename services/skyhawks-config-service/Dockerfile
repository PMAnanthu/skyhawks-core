FROM openjdk:11
RUN mkdir logs
RUN chmod 777 logs
EXPOSE 8300
COPY build/libs/skyhawks-config-service-0.0.1.jar skyhawks-config-service
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=prod","/skyhawks-config-service"]
HEALTHCHECK CMD curl --fail http://localhost:8300/v0.1/actuator || exit 1