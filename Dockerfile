FROM eclipse-temurin:25-jdk

WORKDIR /app

COPY target/lifestats.jar app.jar

ENTRYPOINT ["java","-jar","app.jar"]