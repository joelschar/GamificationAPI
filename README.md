#AMT - Gamification via an API REST

__Authors__: Steve Henriquet, Yann Lederrey, Patrick Neto and Joël Shär.



## Overview

This repo contains a complet infrastructure to run a simple domain model exposed via an API REST, that can be accessed with an API Key. The domain modelize a simple gamification engine that uses typical game elements and mechanics, relevant to improve user experience in various types of application.



## Getting started

Before getting started with the tools, lets take a look at the project structure. In the root level, we have two main directories:

- `docker`: this directory contains the docker configuration files to build à MySQL Server, used to store the data exposed via the REST API. A complete "dockerized" infrastructure to run the Gamification API is available in the directory `gameapi_docker`.
- `swagger`: this directory contains the core of the project: the `spring-server` that will be used to make the API REST service available and the `gametown-specs` directory that contains the API specification, the source code to generate the code needed to run the service.

### Use the components separately

####Run the database service

1) First of all, we need to make a database available to use the infrastructure. For this, a "dockerized" MySQL is used. Let's begin by typing the command:

```bash
cd docker
```

to access to the docker configuration file.

2) For this step, you need Docker tool in your machine (if not, let's download it [here](https://www.docker.com/get-started) ). Verify if the `docker-compose.yaml` is in your repertory you are, and run the command:

```bash
docker-compose up
```

You need to make sure that the port 3307 is not yet used. The needed schema and data will be automatically populated in a database named `GamificationAPIDatabase`. The docker container wil use it to expose the MySQL server. Make sure the container is effectively started by typing:

```bash
docker ps
```

You should have an output like this:

![dockerps-mysql](./readme_img/dockerps-mysql.png)

To make sur that the database is effectively available and populated, you need to access in the conainer. For this, you can run the command:

```bash
docker exec -it amt_gamificationAPI_db /bin/bash
```

Once the bash of the container started, you can access to the mySQL server by typing:

```bash
mysql -p
```

(a password is needed. Juste type `root`). 

#### Run the Spring Boot Server

In the root directory of the repo, let's type de command:

```bash
cd swagger/spring-server/
```

To access at the spring-server project, containing the API Rest implementation. To run the server, juste run the command:

```bash
mvn spring-boot:run
```

Make sure that the port 8080 isn't yet used. Once started, the server will run at the adress http://localhost:8080/api, and will rend a page like that:

![swaggerUI](./readme_img/swaggerUI.png)

###The easy way

Like mentionned earlier, a "ready to use" dockerized infrastructure is available. To run that, you simply need to type the command:

```bash
cd docker/gameapi_docker/
```

To access to the `docker-compose.yml` that will be used to run the containers. Let's type the following command to run the infrastructure:

```
docker-compose up
```

You can access to the service a the same address: http://localhost:8080/api.

###Send a request to the API Rest

You can use curl to invoke the endpoints:

* To retrieve the list of badges (for example) previously created:

  ```
  curl -X GET --header 'Accept: application/json' --header 'apiKey: application01' 'http://localhost:8080/api/users'
  ```

  **Attention:** the access of the API REST is restricted. You need to add the key `application01` in query parameter `apiKey`. 

* To create a new badge (beware that in the live documentation, there are extra \ line separators in the JSON payload that cause issues in some shells)

  ```
  curl -X POST --header 'Content-Type: application/json' --header 'Accept: application/json' --header 'apiKey: application01' -d '{"active": true, "id": 2, "name": "badge02"}' 'http://localhost:8080/api/badges'
  ```

## Runing the tests



## Testing the Gamification API REST

Two kinds of tests are made to a