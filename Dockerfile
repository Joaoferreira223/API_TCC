# Usa Java 17
FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

# Copia todo o projeto
COPY . .

# Builda a aplicação com Maven
RUN ./mvnw clean package -DskipTests

# Copia o .jar gerado para ser executado
RUN cp target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","app.jar"]
