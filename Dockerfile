FROM openjdk:11-slim

WORKDIR /home/app

COPY ./.mvn/ ./build/.mvn/
COPY ./src/ ./build/src/
COPY ./mvnw ./build/
COPY ./pom.xml ./build/

RUN cd ./build/ && ./mvnw clean && ./mvnw package && mv ./target/id.jar ../ && cd ../ && rm -fr ./build/ && mkdir ./config/

CMD [ "java", "-jar", "./id.jar" ]
