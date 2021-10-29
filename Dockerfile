FROM openjdk:17
VOLUME tmp
ADD target/MeFit-0.0.1-SNAPSHOT.jar mefit.jar
ENTRYPOINT ["java", "-jar", "/mefit.jar"]