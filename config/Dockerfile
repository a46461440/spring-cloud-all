FROM 99taxis/mini-java8:1.1

MAINTAINER ZXC 496461440@qq.com

ADD target/*.jar config.jar

EXPOSE 1301

ENTRYPOINT ["java", "-jar", "/config.jar"]