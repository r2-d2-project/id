FROM openjdk:11-slim

WORKDIR /home/app

COPY ./.mvn/ ./.mvn/
COPY ./src/ ./src/
COPY ./mvnw ./
COPY ./pom.xml ./

RUN ./mvnw clean && ./mvnw package -Dmaven.test.skip=true

CMD [ "java", "-jar", "./target/id.jar" ]
