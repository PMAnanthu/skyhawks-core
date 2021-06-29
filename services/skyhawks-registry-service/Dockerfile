FROM openjdk:11
EXPOSE 8761
COPY build/libs/skyhawks-registry-service-0.0.1.jar skyhawks-registry-service.jar
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=prod","/skyhawks-registry-service.jar"]