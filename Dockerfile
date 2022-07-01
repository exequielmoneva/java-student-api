FROM openjdk:17
ADD target/java-student-api.jar java-student-api.jar
ENTRYPOINT ["java", "-jar","java-student-api.jar"]
EXPOSE 8080