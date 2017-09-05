FROM java:8
VOLUME /tmp
ADD target/IbmSkillsMatrix.jar app.jar
RUN bash -c 'app.jar'
EXPOSE 8080
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]