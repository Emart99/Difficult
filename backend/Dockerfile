FROM openjdk:14-jdk-alpine
COPY . /backend
WORKDIR /backend
RUN ./gradlew build
CMD ./gradlew run