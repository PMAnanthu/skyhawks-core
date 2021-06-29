FROM openjdk:11
RUN mkdir logs
RUN chmod 777 logs
EXPOSE 8250
EXPOSE 5432/tcp
COPY build/libs/skyhawks-user-service-0.0.1.jar skyhawks-user-service.jar
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=prod ","/skyhawks-user-service.jar"]
HEALTHCHECK CMD curl --fail http://localhost:8250/v0.1/actuator || exit 1