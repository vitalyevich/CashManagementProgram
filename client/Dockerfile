FROM openjdk:17
WORKDIR /workspace/app
ADD target/client.jar client.jar
ENTRYPOINT ["java","-jar","/client.jar"]
