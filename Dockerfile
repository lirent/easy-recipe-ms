FROM openjdk:11
ARG JAR_FILE=/target/*.jar

COPY ${JAR_FILE} /home/recipe-manager.jar
WORKDIR /home/

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "recipe-manager.jar"]