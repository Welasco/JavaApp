FROM openjdk:11

WORKDIR /app

COPY . .

RUN chmod +x /app/mvnw \
    && ./mvnw clean package \
    && cp target/javaapp-0.0.1-SNAPSHOT.jar javaapp.jar

EXPOSE 8080

ENTRYPOINT ["java", "-javaagent:applicationinsights-agent-3.2.11.jar", "-jar", "javaapp.jar"]