# Usar uma imagem do OpenJDK 21
FROM openjdk:21

# Criar um diretório dentro do container
WORKDIR /app

# Copiar o JAR gerado para dentro do container
COPY target/PowerPath-1.0-SNAPSHOT.jar app.jar

# Definir o comando para rodar a API
CMD ["java", "-jar", "app.jar"]
