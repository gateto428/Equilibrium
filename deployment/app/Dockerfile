FROM openjdk:11
RUN adduser --system --group spring
USER spring:spring
COPY equilibrium.jar /equilibrium.jar
ENTRYPOINT ["java", "-jar", "/equilibrium.jar"]
