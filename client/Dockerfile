FROM openjdk:17
EXPOSE 8080
RUN addgroup -S springdocker && adduser -S springdocker -G springdocker
USER springdocker:springdocker
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} client.jar
ENTRYPOINT ["java","-jar","/client.jar"]