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
docker run -p 8083:8083 -e AWS_ACCESS_KEY_ID=[AWS_ACCESS_KEY] -e AWS_SECRET_ACCESS_KEY=[AWS_SECRET_ACCESS_KEY] sr-spring-boot-docker
```

build docker image with maven
```shell script
./mvnw com.google.cloud.tools:jib-maven-plugin:dockerBuild -Dimage=sr-spring-boot-docker
```

# Set up docker image and deploy to GKE
### build image and push to gcr
```shell script
export PROJECT_ID=[PROJECT_ID]
docker build -t gcr.io/${PROJECT_ID}/mc-app2:v1 .
docker images
docker push gcr.io/${PROJECT_ID}/mc-app2:v1
```
### run locally
```shell script
docker run --rm -p 8083:8083 -e AWS_ACCESS_KEY=[AWS_ACCESS_KEY] -e AWS_SECRET_ACCESS_KEY=[AWS_SECRET_ACCESS_KEY] gcr.io/${PROJECT_ID}/mc-app2:v1
```
### deploy to eks with istio-enabled
```shell script
kubectl create ns sample
kubectl label namespace sample istio-injection=enabled
kubectl create deployment mc-web2 --image=gcr.io/${PROJECT_ID}/mc-app2:v1 -n sample
```
### expose deployment
```shell script
kubectl get pods -n sample
kubectl expose deployment mc-web2 --type=LoadBalancer --port 80 --target-port 8083 -n sample
kubectl get service -n sample
```
### deploy new version
```shell script
docker build -t gcr.io/${PROJECT_ID}/mc-app2:v3 .
docker push gcr.io/${PROJECT_ID}/mc-app2:v3
kubectl set image deployment/mc-web mc-app=gcr.io/${PROJECT_ID}/mc-app2:v3
```
### clean up
```shell script
kubectl delete service mc-web
```