# Usa Java 17
FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

# Copia apenas o necessário primeiro (melhor cache)
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# Dá permissão de execução ao mvnw
RUN chmod +x mvnw

# Copia o código
COPY src src

# Build da aplicação
RUN ./mvnw clean package -DskipTests

# Renomeia o jar
RUN cp target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","app.jar"]
