FROM openjdk:17
WORKDIR /app
RUN chmod +x mvnw
RUN ./mvnw package
COPY target/spring-boot-amazon-s3-react-0.0.1-SNAPSHOT.jar .
EXPOSE 8080
ENTRYPOINT ["java","-jar","spring-boot-amazon-s3-react-0.0.1-SNAPSHOT.jar"]
