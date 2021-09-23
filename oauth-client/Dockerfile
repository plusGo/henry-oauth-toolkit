FROM java:8

EXPOSE 8080

VOLUME /tmp

ENV TZ=Asia/Shanghai

ADD  /target/*.jar /app.jar

ENTRYPOINT ["java","-jar","/app.jar"]