FROM eclipse-temurin:17-jdk-alpine
COPY target/*.jar inpost-interview-gk.jar
ENTRYPOINT ["java","-jar","/inpost-interview-gk.jar"]