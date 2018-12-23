# FROM registry.cn-hangzhou.aliyuncs.com/joint-union/java8:latest
FROM registry.cn-hangzhou.aliyuncs.com/zxcxcg/java8:1.1

ADD target/*.jar eureka.jar

EXPOSE 8761

ENTRYPOINT ["java", "-jar", "/eureka.jar"]