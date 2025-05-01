# Use Maven image with JDK 21 to compile and test
FROM maven:3.9.4-eclipse-temurin-21

WORKDIR /app

COPY . .

CMD ["mvn", "clean", "test"]
