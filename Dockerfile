FROM openjdk:17
WORKDIR /var/www/app
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src

RUN chmod +x mvnw
RUN ./mvnw package -Dmaven.test.skip

EXPOSE 8080

COPY var/www/app/target/*.jar app.jar

CMD ["java", "-jar", "app.jar"]
