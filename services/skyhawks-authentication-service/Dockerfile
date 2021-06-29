FROM openjdk:11
RUN mkdir logs
RUN chmod 777 logs
EXPOSE 8000
EXPOSE 5432/tcp
COPY build/libs/skyhawks-authentication-service-0.0.1.jar skyhawks-authentication-service.jar
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=prod","/skyhawks-authentication-service.jar"]
HEALTHCHECK CMD curl --fail http://localhost:8000/v0.1/actuator || exit 1