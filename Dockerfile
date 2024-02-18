FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY build/libs/storyteller-api-0.0.1-SNAPSHOT.jar storyteller-api-0.0.1-SNAPSHOT.jar
EXPOSE 80
CMD ["java","-jar","storyteller-api-0.0.1-SNAPSHOT.jar"]