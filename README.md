# Distributed systems lab

Testing is possible with `./gradlew bootRun` inside the demo folder

The API definition is then available under: [http://localhost:8090/swagger-ui/index.html#/](http://localhost:8090/swagger-ui/index.html#/)

## Lab tasks:

- Build your own Spring Boot application starting at the Spring Initializr (start.spring.io)

- Add the following dependencies: Spring Web, Actuator, DevTools

- Run the microservice and test the endpoint /actuator/health and /actuator/info

- Annotate the Microservice with @RestController and implement a "Hello, World!" method, which responds to an HTTP GET request.

- Add some basic "functionality" to make the service react to HTTP GET, POST calls (e.g. modify an internal list). Optional: PUT and DELETE

- Change the server port to 8090

- Add the Swagger UI as described in the Baeldung tutorial

- Add an echo endpoint that returns a given parameter using the @PathVariable annotation
