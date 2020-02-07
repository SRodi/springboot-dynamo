[![Build Status](http://159.65.143.184:8080/buildStatus/icon?job=springboot-dynamodb)](http://159.65.143.184:8080/job/springboot-dynamodb/)
# SpringBoot microservice sample application
This application enables DynamoDB (AWS hosted database) CRUD operations.

## Prerequisites
* DynamoDB Table : `MulticloudTest` with `hashKey: "Name"` of type `String` in region `eu-west-1`
* Valid `AWS_ACCESS_KEY_ID` and `AWS_SECRET_ACCESS_KEY` to be set as environment variable or running `aws configure`

## Run
Use either SpringBoot or Maven configurations to run locally:

## Usage
App will run on `http://localhost:8083`. Endpoints and parameters are described below.

##### Create table entry
endpoint:
`PUT http://localhost:8083/write/`

request body:
```
{
    "name": "your_name",
    "description": "your_description"
}
```

##### Read table entry
endpoint:
`GET http://localhost:8083/read/{name}`

request body:
```
{
    "name": "name",
    "description": "description"
}
```

##### Delete table entry
endpoint:
`DELETE http://localhost:8083/delete/{name}`


## Build 
build executable
```shell script
./mvnw package && java -jar target/sr-spring-boot-docker-0.1.0.jar
```
build docker image
```shell script
docker build -t sr-spring-boot-docker .
```
run image locally
```shell script
docker run -p 8083:8083 -e AWS_ACCESS_KEY_ID=xyz -e AWS_SECRET_ACCESS_KEY=aaa sr-spring-boot-docker
```

build docker image with maven
```shell script
./mvnw com.google.cloud.tools:jib-maven-plugin:dockerBuild -Dimage=sr-spring-boot-docker
```