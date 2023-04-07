FROM openjdk:17-jdk-slim as build
WORKDIR /workspace/app

COPY mvnw mvnw.cmd ./
COPY .mvn .mvn
COPY pom.xml .
COPY src src
COPY . .

RUN sed -i 's/\r$//' mvnw
RUN /bin/sh mvnw dependency:resolve
RUN ./mvnw install -DskipTests
RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)

FROM openjdk:17-jdk-slim
VOLUME /tmp
ARG DEPENDENCY=/workspace/app/target/dependency
COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app
ENTRYPOINT ["java","-cp","app:app/lib/*","de.htwberlin.productService.FinalWebshopApplication"]