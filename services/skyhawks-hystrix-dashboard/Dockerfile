FROM openjdk:11
RUN mkdir logs
RUN chmod 777 logs
EXPOSE 8150
COPY build/libs/skyhawks-hystrix-dashboard-0.0.1.jar skyhawks-hystrix-dashboard.jar
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=prod","/skyhawks-hystrix-dashboard.jar"]
HEALTHCHECK CMD curl --fail http://localhost:8150/v0.1/actuator || exit 1