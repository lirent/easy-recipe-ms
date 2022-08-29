# Recipe Manager

This is a RESTful standalone Java application for managing recipes.

* Manages the favourite recipes and able to filter based on one or more criteria.
* Production ready and Docker build
* Provides interface to customize the `SearchEngine`
* Flexible and extendable Interfaces

To build this application my framework of choice is Spring Boot.

## Demo Available
Check out the [Recipe-Manager App Demo](https://recipe-manager0.herokuapp.com/) available on Heroku.

The exposed endpoint is `HOST:8080/api/v1/recipes`
```shell
https://recipe-manager0.herokuapp.com/
```

## Requirements

For building and running the application you need:

- [JDK 11](https://www.oracle.com/java/technologies/downloads/#java11)
- [Maven 3](https://maven.apache.org) or higher
- Git

## Running the application locally

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `net.lirent.recipemanager` class from your IDE.

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn spring-boot:run
```

## Deploying the application with Docker

The easiest way to deploy the application is using Docker:

### First build the app:
```shell
mvn clean package
```
### Create the docker image:
```shell
docker build -f .\Dockerfile - recipe-manager
```
### Run the Docker image on port 8080
```shell
docker run -d -p 8080:8080 -t recipe-manager
```

This will create:

* A container with embedded Tomcat for called "recipe-manager"
* In develop profile the database is in memory
* In production a container with Database


## API's
All the API's implemented are available on th [Swagger-UI interface]( http://localhost:8080/swagger-ui/)
```shell
http://localhost:8080/swagger-ui/
```

## Copyright

Released under the MIT License. See the [LICENSE]( https://gitlab.com/lirent/infosys-recipe-manager/-/blob/a1230b2f9905e2e995130813dd7c07e16ecdc7f4/LICENSE) file.
