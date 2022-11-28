FROM gradle:7.4.2-jdk17

WORKDIR /java-web-ru/deploy

RUN cd /java-web-ru/deploy && gradle installDist

CMD /java-web-ru/deploy/build/install/deploy/bin/deploy