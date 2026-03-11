FROM eclipse-temurin:17-jdk
WORKDIR /app
COPY . .
RUN chmod +x mvnw
RUN ./mvnw clean package -DskipTests
ARG JAR_FILE=target/*.jar
RUN cp ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","app.jar"]