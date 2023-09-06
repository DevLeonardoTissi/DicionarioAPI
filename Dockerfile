FROM openjdk:19
EXPOSE 8080
ADD /build/libs/DicionarioAPI-0.0.1-SNAPSHOT.jar dicionario.jar
ENTRYPOINT ["java", "-jar", "dicionario.jar"]