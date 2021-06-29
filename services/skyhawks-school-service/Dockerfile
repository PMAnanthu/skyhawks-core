FROM openjdk:11
RUN mkdir logs
RUN chmod 777 logs
EXPOSE 8100
EXPOSE 5432/tcp
COPY build/libs/skyhawks-school-service-0.0.1.jar skyhawks-school-service.jar
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=prod ","/skyhawks-school-service.jar"]
HEALTHCHECK CMD curl --fail http://localhost:8100/v0.1/actuator || exit 1