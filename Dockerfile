FROM openjdk:17-jdk-slim as builder
WORKDIR bookshopapplication
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} bookshopapplication.jar
RUN java -Djarmode=layertools -jar bookshopapplication.jar extract

FROM openjdk:17-jdk-slim
WORKDIR BookShopApplication
COPY --from=builder bookshopapplication/dependencies/ ./
COPY --from=builder bookshopapplication/spring-boot-loader/ ./
COPY --from=builder bookshopapplication/snapshot-dependencies/ ./
COPY --from=builder bookshopapplication/bookshopapplication/ ./
ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]
EXPOSE 8080