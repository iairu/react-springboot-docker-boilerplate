# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the Maven wrapper and the project description file from the demo subdirectory
COPY demo/.mvn/ .mvn
COPY demo/mvnw demo/pom.xml ./

RUN chmod +x ./mvnw

# Download the dependencies
RUN ./mvnw dependency:go-offline

# Copy the rest of the application's source code from the demo subdirectory
COPY demo/src ./src

# Package the application
RUN ./mvnw package -DskipTests

# Set the entry point for the container
ENTRYPOINT ["java", "-XX:-UseContainerSupport", "-jar", "target/demo-0.0.1-SNAPSHOT.jar"]
