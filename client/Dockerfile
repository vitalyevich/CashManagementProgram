FROM maven:3.8.5-openjdk-17 AS maven_build
COPY pom.xml /tmp/
COPY src /tmp/src/
WORKDIR /tmp/
RUN mvn package

#pull base image

FROM openjdk
MAINTAINER maksim_2411@mail.ru
EXPOSE 8080
CMD java -jar /data/client.jar
COPY --from=maven_build /tmp/target/client.jar /data/client.jar
