FROM gradle:7.4.2-jdk17

WORKDIR /java-web-ru/deploy

RUN ./gradlew installDist

CMD ./build/install/deploy/bin/deploy