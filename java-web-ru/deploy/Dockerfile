FROM gradle:7.4.2-jdk17

WORKDIR /java-web-ru/deploy

RUN make install

CMD ./build/install/deploy/bin/deploy