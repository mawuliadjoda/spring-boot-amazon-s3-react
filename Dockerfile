
#
# Build stage
#
FROM openjdk:17
COPY . .
RUN chmod 777
RUN mvn clean package -DskipTests

#
# Package stage
#

COPY --from=build /target/*.jar app.jar
# ENV PORT=8080
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]