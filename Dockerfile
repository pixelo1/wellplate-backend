FROM openjdk:21-jdk-slim

VOLUME /tmp

COPY build/libs/wellplate-0.0.1-SNAPSHOT.jar /app/wellplate-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java", "-jar", "/app/wellplate-0.0.1-SNAPSHOT.jar"]