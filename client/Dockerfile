FROM maven:3.8.5-openjdk-17 AS maven_build
WORKDIR /client
MAINTAINER maksim_2411@mail.ru
EXPOSE 8080

CMD java -jar client.jar

COPY --from=maven_build target/client.jar client.jar
ENTRYPOINT ["java","-jar","/client.jar"]
