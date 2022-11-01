FROM maven:3.8.5-openjdk-17 AS maven_build

COPY pom.xml /tmp/

COPY src /tmp/src/

WORKDIR /tmp/

RUN mvn package

#pull base image

FROM openjdk

#maintainer 
MAINTAINER maksim_2411@mail.ru
#expose port 8080
EXPOSE 8080

#default command
CMD java -jar /data/client.jar

#copy hello world to docker image from builder image

COPY --from=maven_build /tmp/target/client.jar /data/client.jar
