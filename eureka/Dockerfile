# FROM registry.cn-hangzhou.aliyuncs.com/joint-union/java8:latest
FROM 99taxis/mini-java8:1.1

ADD target/*.jar eureka.jar

EXPOSE 8761

ENTRYPOINT ["java", "-jar", "/eureka.jar"]