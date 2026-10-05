FROM eclipse-temurin:21-jre-jammy
        
EXPOSE 8080
 
ENV APP_HOME /usr/src/app
# Agent OpenTelemetry (traces)
ADD https://github.com/open-telemetry/opentelemetry-java-instrumentation/releases/latest/download/opentelemetry-javaagent.jar /opt/otel/opentelemetry-javaagent.jar
COPY target/*.jar $APP_HOME/app.jar
WORKDIR $APP_HOME
CMD ["java", "-javaagent:/opt/otel/opentelemetry-javaagent.jar",  "-jar", "app.jar"]
