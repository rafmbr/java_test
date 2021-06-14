FROM openjdk:8-jdk-alpine
MAINTAINER rafmbr.com
COPY target/test_java-0.0.1-SNAPSHOT.jar stock-quote-manager-1.0.0.jar
ENTRYPOINT ["java","-jar","/stock-quote-manager-1.0.0.jar"]