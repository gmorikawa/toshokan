FROM eclipse-temurin:17-jdk-jammy

WORKDIR /server

USER root

COPY . .

ENTRYPOINT [ "./mvnw", "spring-boot:run" ]
