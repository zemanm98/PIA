FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
ARG JAR_FILE=api/target/*.jar
COPY ${JAR_FILE} /opt/app.jar
ENTRYPOINT ["java", "-jar" , "/opt/app.jar"]