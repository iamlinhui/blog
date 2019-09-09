# Dockerfile for blog
# 1. mvn clean package -Dmaven.test.skip=true
# 2. Build with: docker build -f Dockerfile -t blog .
# 3. Run with: docker run -p 8080:8080 -d -v /tmp/logs/blog:/logs/blog --name blog blog

FROM openjdk:8-jre-alpine
MAINTAINER lionel <lynn@promptness.cn>

ENV TIME_ZONE=PRC
RUN ln -snf /usr/share/zoneinfo/$TIME_ZONE /etc/localtime && echo $TIME_ZONE > /etc/timezone

ENV SERVER_PORT 8080
EXPOSE $SERVER_PORT

ADD target/blog-*.war /home/app/blog.war

ENV APOLLO="-Denv=dev \
            -Dapollo.meta=http://apollo-host:port \
            -Dapp.id=blog \
            -Dapollo.cluster=promptness \
            -Dapollo.cacheDir=/opt/properties/ \
            -Dapollo.bootstrap.enabled=true \
            -Dapollo.bootstrap.eagerLoad.enabled=true \
            -Dapollo.bootstrap.namespaces=application"

ENV OPTION="-Dnetworkaddress.cache.ttl=600 \
            -Djava.security.egd=file:/dev/./urandom \
            -Djava.awt.headless=true \
            -Duser.timezone=Asia/Shanghai \
            -Dclient.encoding.override=UTF-8 \
            -Dfile.encoding=UTF-8"

ENV VIRTUAL="-XX:+UseG1GC \
             -XX:MaxGCPauseMillis=200"

ENTRYPOINT ["sh","-c","java -server -d64 $VIRTUAL $APOLLO $OPTION -jar /home/app/blog.war"]