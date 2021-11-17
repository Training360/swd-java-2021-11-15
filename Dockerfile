FROM azul/zulu-openjdk:17.0.0
WORKDIR app
COPY mvnw pom.xml .
COPY .mvn .mvn
RUN ["./mvnw", "verify", "clean", "--fail-never"]
COPY . .
RUN chmod +x mvnw
ENTRYPOINT ["./mvnw", "test"]
