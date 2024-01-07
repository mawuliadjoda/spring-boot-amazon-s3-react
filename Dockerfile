FROM openjdk:21
WORKDIR /app
RUN ./mvnw package
COPY target/spring-boot-amazon-s3-react-0.0.1-SNAPSHOT.jar .
EXPOSE 8080
ENTRYPOINT ["java","-jar","spring-blog-tdd-0.0.1-SNAPSHOT.jar"]