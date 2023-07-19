FROM openjdk:17-slim

ENV JAVA_OPTS " -Xms1024m -Xmx1024m -Djava.security.edg-file:/dev/./urandom"

RUN apt-get update && apt-get install libcurl4-openssl-dev -y

WORKDIR /application

COPY ./target/spring-practice-recipes-0.0.1-SNAPSHOT.jar ./

ENTRYPOINT ["java", "-jar", "spring-practice-recipes-0.0.1-SNAPSHOT.jar"]