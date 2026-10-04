FROM eclipse-temurin:21-jre-jammy
        
EXPOSE 8000
 
ENV APP_HOME /usr/src/app
COPY target/*.jar $APP_HOME/app.jar
WORKDIR $APP_HOME
CMD ["java", "-jar", "app.jar"]
