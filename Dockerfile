FROM openjdk:19
WORKDIR /app
EXPOSE 8080
COPY . .

ADD /build/libs/DicionarioAPI-0.0.1-SNAPSHOT.jar dicionario.jar
ENTRYPOINT ["java", "-jar", "dicionario.jar"]