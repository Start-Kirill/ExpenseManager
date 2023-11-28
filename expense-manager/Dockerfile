FROM eclipse-temurin:19-jre-focal
MAINTAINER Kiryl

ADD ./target/expense-manager-0.0.1-SNAPSHOT.jar /app/

CMD ["java", "-Xmx200m", "-jar", "/app/expense-manager-0.0.1-SNAPSHOT.jar"]

EXPOSE 8080 8089