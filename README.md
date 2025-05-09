# Test-Kosmos-01
Project created to make the kosmos test for job appliance

# ALL YOU NEED
```
-Docker
-Docker-compose (if you use Linux)
-mvn
-Java 17
```
# Instructions to execute
```
1.- In the main folder of the repo execute the DataBase Image

    docker-compose up -d test-db

2.- After this execute the db-migration project

Before this step please wait at least 5 minutes in order to let the docker image of the database get ready

3.- After the db-migration project is executed (when is running)
execute the main project "consultorio"

```
