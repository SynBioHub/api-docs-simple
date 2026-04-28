# syntax=docker/dockerfile:1

FROM maven:3.9.9-eclipse-temurin-17 AS build
WORKDIR /build

# Copy Maven descriptor first for better layer caching
COPY generated/pom.xml ./pom.xml

# Copy source files
COPY generated/src ./src

# Build app and collect runtime dependencies
RUN mvn -q -DskipTests clean package dependency:copy-dependencies -DincludeScope=runtime

FROM eclipse-temurin:17-jre
WORKDIR /app

# Spring server port used by plugin template
EXPOSE 5000

# Copy compiled classes and runtime dependencies
COPY --from=build /build/target/classes ./classes
COPY --from=build /build/target/dependency ./dependency

# Run Spring Boot app on port 5000
ENTRYPOINT ["java", "-cp", "/app/classes:/app/dependency/*", "org.synbiohub.PluginApplication", "--server.port=5000"]
