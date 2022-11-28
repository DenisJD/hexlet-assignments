FROM gradle:7.4.2-jdk17

WORKDIR /java-web-ru/deploy

RUN gradle installDist

CMD ./build/install/deploy/bin/deploy