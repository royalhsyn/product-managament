FROM alpine:3.16.0
RUN apk add --no-cache java-cacerts openjdk17-jdk
COPY build/libs/productmanagement-0.0.1-SNAPSHOT.jar /app/
ENTRYPOINT ["java"]
CMD ["-jar" , "/app/productmanagement-0.0.1-SNAPSHOT.jar"]




