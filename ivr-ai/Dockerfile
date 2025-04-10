FROM eclipse-temurin:21-jdk

WORKDIR /app

COPY target/ivr-ai-0.0.1-SNAPSHOT.jar app.jar

ENV SPRING_PROFILES_ACTIVE=default

ENTRYPOINT ["java", "-jar", "app.jar"]