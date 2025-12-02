FROM eclipse-temurin:17-jdk-jammy

ARG USERNAME=toshokan
ARG GROUPNAME=toshokan
ARG UID=1000
ARG GID=1000
ARG FILE_DIR=data

RUN groupadd -g $GID $GROUPNAME && \
    useradd -m -s /bin/bash -u $UID -g $GID $USERNAME

USER toshokan

WORKDIR /toshokan/server

COPY . .

RUN mkdir -p $FILE_DIR && \
    chown -R $USERNAME:$GROUPNAME $FILE_DIR && \
    ./mvnw package -DskipTests && \
    cp target/*.jar app.jar

ENTRYPOINT java -jar /toshokan/server/app.jar
