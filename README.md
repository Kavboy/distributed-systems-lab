# Distributed systems lab

Testing is possible with `./gradlew bootRun`

To run the docker container first you need to execute `./gradlew build` to build the Java jar.
Then to build the docker image run:
`docker build --build-arg JAR_FILE=build/libs/demo-0.0.1-SNAPSHOT.jar -t kavboy/demo-spring-docker .`

To run the container execute `docker run --name demo-spring-docker -p 8090:8090 kavboy/demo-spring-docker`
If you want to run it in background add `-d`

The API definition is available under: [http://localhost:8090/swagger-ui/index.html#/](http://localhost:8090/swagger-ui/index.html#/)

To stop the container run `docker stop demo-spring-docker`

To remove the container execute `docker rm demo-spring-docker`

To remove the image execute `docker rmi kavboy/demo-spring-docker`

## Lab tasks:

- Add Spring Boot app to docker container
