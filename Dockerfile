# Etapa 1: Compilação com Maven e Java 17
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY . .

# Entra diretamente na pasta do NetBeans para compilar
RUN cd VideoSharer && mvn clean package -DskipTests

# Etapa 2: Execução com Tomcat 9 e Java 17
FROM tomcat:9-jdk17
RUN rm -rf /usr/local/tomcat/webapps/*

# Copia o arquivo .war gerado de dentro da pasta VideoSharer
COPY --from=build /app/*.war /usr/local/tomcat/webapps/ROOT.war

EXPOSE 8080
CMD ["catalina.sh", "run"]

