FROM gradle:7.4.2-jdk17

WORKDIR /java-web-ru/session

COPY /java-web-ru/session .

RUN gradle installDist

CMD ./build/install/session/bin/session