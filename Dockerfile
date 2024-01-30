FROM openjdk:21-jdk-slim-buster

ARG NOME_ARQUIVO_JAR
ENV FILE_JAR=$NOME_ARQUIVO_JAR
COPY target/$FILE_JAR /app/app.jar
WORKDIR /app
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]