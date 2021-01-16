FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY target/FEBS-Shiro-2.0.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar", "--spring.profiles.active=dev"]