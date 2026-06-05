FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

COPY TemperatureApi.java .

RUN javac TemperatureApi.java

EXPOSE 8081

CMD ["java", "TemperatureApi"]