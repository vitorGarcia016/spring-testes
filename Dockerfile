FROM maven:4.0.0-rc-5-amazoncorretto-21-al2023 as build
WORKDIR /build
COPY . .
RUN mvn clean package

FROM amazoncorretto:21-alpine3.21
COPY --from=build /build/target/*.jar /app/app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","app.jar"]

