FROM java:8
VOLUME /tmp
ADD *.jar ./app.jar
ADD *.properties ./
RUN bash -c 'touch ./app.jar'
EXPOSE 8761
CMD ["java","-Djava.security.egd=file:/dev/./urandom","-jar","./app.jar"]