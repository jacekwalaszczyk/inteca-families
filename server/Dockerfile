FROM maven:3.5.4-jdk-8-alpine

WORKDIR /app
COPY ./ /app/
RUN mvn package

RUN cp /app/target/*.jar ./app.jar
EXPOSE 8080

CMD ["java", "-jar", "app.jar"]