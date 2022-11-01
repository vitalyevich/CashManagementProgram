FROM maven:3.8.5-openjdk-17 AS maven_build
WORKDIR /client/
RUN mvn package

FROM openjdk
MAINTAINER maksim_2411@mail.ru
EXPOSE 8080
CMD java -jar /data/client.jar
COPY --from=maven_build /client/target/client.jar /data/client.jar
