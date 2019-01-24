#AMT - Gamification via an API REST

__Authors__: Steve Henriquet, Yann Lederrey, Patrick Neto and Joël Shär.

## Overview

This repo contains a complet infrastructure to run a simple domain model exposed via an API REST, that can be accessed with an API Key. The domain modelize a simple gamification engine that uses typical game elements and mechanics, relevant to improve user experience in various types of application.

## Getting started

Before getting started with the tools, lets take a look at the project structure. In the root level, we have two main directories:

- `docker`: this directory contains the docker configuration files to build à MySQL Server, used to store the data exposed via the REST API. A complete "dockerized" infrastructure to run the Gamification API is available in the directory `gameapi_docker`.
- `swagger`: this directory contains the core of the project: the `spring-server` that will be used to make the API REST service available and the `gametown-specs` directory that contains the API specification, the source code to generate the code needed to run the service.

####Run the database service

1) First of all, we need to make a database available to use the infrastructure. For this, a "dockerized" MySQL is used. Let's begin by typing the command:

```bash
cd docker
```

to access to the docker configuration file.

2) For this step, you need Docker tool in your machine (if not, let's download it [here](https://www.docker.com/get-started) ). Verify if the `docker-compose.yaml` is in your repertory you are, and run the command:

```
docker-compose up
```

You need to make sure that the port 3307 is not yet used. The docker container wil use it to expose the MySQL server.











# Build and run the Fruit microservice

You can use maven to build and run the REST API implementation from the command line. After invoking the following maven goal, the Spring Boot server will be up and running, listening for connections on port 8080.

```
cd swagger/spring-server/
mvn spring-boot:run
```

You can then access:

* the [API documentation](http://localhost:8080/api/swagger-ui.html), generated from annotations in the code
* the [API endpoint](http://localhost:8080/api/), accepting GET and POST requests

You can use curl to invoke the endpoints:

* To retrieve the list of fruits previously created:

```
curl -X GET --header 'Accept: application/json' 'http://localhost:8080/api/fruits'
```

* To create a new fruit (beware that in the live documentation, there are extra \ line separators in the JSON payload that cause issues in some shells)

```
curl -X POST --header 'Content-Type: application/json' --header 'Accept: */*' -d '{
   "colour": "orange",
   "kind": "orange", 
   "size": "small", 
   "weight": "medium" 
 }' 'http://localhost:8080/api/fruits'
```

# Test the Fruit microservice by running the executable specification

You can use the Cucumber project to validate the API implementation. Do this when the server is running.

```
cd cd swagger/fruits-specs/
mvn clean test
```
You will see the test results in the console, but you can also open the file located in `./target/cucumber/index.html`
