# Spring Camel


## Database
- Create database in docker
```
docker container run -d -p 5432:5432 -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=123456 --name postgres_camel postgres
````
