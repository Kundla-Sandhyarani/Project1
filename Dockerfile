FROM openjdk:11-jdk-slim
COPY target/*.jar app.jar
CMD sh -c "sleep infinity && java -jar /app/app.jar"
ENTRYPOINT ["java", "-jar", "/app.jar"]
