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

# Install netcat for connection checking
RUN apk add --no-cache netcat-openbsd bash

# Copy the built JAR from builder stage
COPY --from=builder /build/target/core-1.0-SNAPSHOT.jar app.jar

# Copy entrypoint script
COPY entrypoint.sh .
RUN chmod +x entrypoint.sh

# Expose the port
EXPOSE 8080

# Use entrypoint script to wait for database
ENTRYPOINT ["./entrypoint.sh"]
