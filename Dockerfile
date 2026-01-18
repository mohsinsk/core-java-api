# Stage 1: Build the application
FROM maven:3.9-eclipse-temurin-17 as builder
WORKDIR /build

# Copy pom.xml and source code
COPY pom.xml .
COPY src/ src/

# Build the application
RUN mvn clean package -DskipTests

# Stage 2: Run the application
FROM amazoncorretto:17-alpine
WORKDIR /app

# Copy the built JAR from builder stage
COPY --from=builder /build/target/core-1.0-SNAPSHOT.jar app.jar

# Expose the port
EXPOSE 8080

# Command to run the app
ENTRYPOINT ["java", "-jar", "app.jar"]