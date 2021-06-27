FROM openjdk:11
RUN mkdir logs
RUN chmod 777 logs
COPY build/libs/skyhawks-school-service.jar skyhawks-school-service.jar
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=prod","/skyhawks-school-service.jar"]
HEALTHCHECK CMD curl --fail http://localhost:1000/v0.1/actuator || exit 1