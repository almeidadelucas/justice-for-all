FROM azul/zulu-openjdk:17.0.6-jre

WORKDIR /app

# Setting Maven and the dependencies of the project
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:go-offline

# Setting the Sources
COPY src ./src

# Starting the Spring application
CMD ["./mvnw", "spring-boot:run"]