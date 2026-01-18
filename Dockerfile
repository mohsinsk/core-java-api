FROM amazoncorretto:17-alpine
# Set working directory
WORKDIR /app

# Copy the Fat JAR from target folder to the image
COPY target/core-1.0-SNAPSHOT.jar app.jar

# Expose the port
EXPOSE 8080

# Command to run the app
ENTRYPOINT ["java", "-jar", "app.jar"]