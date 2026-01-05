# Usa Java 17
FROM eclipse-temurin:17-jdk-alpine

# Cria a pasta da aplicação
WORKDIR /app

# Copia o arquivo .jar gerado pelo Maven
COPY target/*.jar app.jar

# Expõe a porta 8080
EXPOSE 8080

# Comando para rodar a API
ENTRYPOINT ["java","-jar","app.jar"]
