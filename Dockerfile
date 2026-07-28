FROM maven:3.8.5-openjdk-17 AS build

WORKDIR /app

COPY . .

RUN mvn clean package -DskipTests


FROM eclipse-temurin:17-jre

WORKDIR /app

COPY --from=build /app/target/VideoSharer.war app.war

EXPOSE 8080

CMD ["java", "-jar", "app.war"]
