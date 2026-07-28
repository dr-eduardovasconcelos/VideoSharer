# ==========================================
# Etapa 1 - Compilação com Maven e Java 17
# ==========================================
FROM maven:3.8.5-openjdk-17 AS build

WORKDIR /app

# Copia o projeto Spring Boot
COPY . .

# Compila o WAR
RUN mvn clean package -DskipTests


# ==========================================
# Etapa 2 - Execução com Tomcat 9 e Java 17
# ==========================================
FROM tomcat:9-jdk17

# Remove as aplicações padrão do Tomcat
RUN rm -rf /usr/local/tomcat/webapps/*

# Copia o WAR gerado pelo Maven
COPY --from=build /app/target/*.war \
    /usr/local/tomcat/webapps/ROOT.war

EXPOSE 8080

CMD ["catalina.sh", "run"]
