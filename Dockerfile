FROM eclipse-temurin:17-jdk-alpine
WORKDIR /APP
COPY target/mortgageCalculator-0.0.1-SNAPSHOT.jar mortgageCalculator-0.0.1-SNAPSHOT.jar
EXPOSE 8080
CMD ["java", "-jar", "mortgageCalculator-0.0.1-SNAPSHOT.jar"]
