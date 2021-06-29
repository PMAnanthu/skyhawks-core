FROM openjdk:11
RUN mkdir logs
RUN chmod 777 logs
EXPOSE 8761
COPY build/libs/skyhawks-registry-service-0.0.1.jar skyhawks-registry-service.jar
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=prod","/skyhawks-registry-service.jar"]
HEALTHCHECK CMD curl --fail http://localhost:8761/actuator || exit 1