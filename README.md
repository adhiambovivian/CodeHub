# CodeHub

Pre-requisites
- Java > version 8
- Rabbitmq
- Postgresql
- redis
- solr
- Mongodb
- mySQL

# install maven

```export PATH=$PATH:/opt/apache-maven/bin1```

# install rabbitmq
```
docker run -d --hostname rabbitmq --name rammitmq -p 15672:15672 -p 5672:5672 rabbitmq:3-management
```
- use default rabbitmq container credentials; guest

# install solr
```
docker run -p 8983:8983 -t solr
```

```
docker exec -it --user=solr <container-id> bin/solr create_core -c participants
```

# Install mysql
```
docker run --name mysql -p 3306:3306 -e MYSQL_ROOT_PASSWORD=your-password -d mysql:latest
```

# To access mysql terminal
```docker exec -it mysql bash
mysql -u root -p
CREATE USER 'your-username'@'localhost' IDENTIFIED BY 'your-password';
GRANT ALL PRIVILEGES ON * . * TO 'your-username'@'localhost';
FLUSH PRIVILEGES;
```

# Running the application using Maven

```
mvn spring-boot:run -Drun.jvmArguments='-Dserver.port=7090'
```

### Using the maven wrapper to package the app on unix ###
```
./mvnw clean install
```

### Using maven wrapper to run spring-boot ###

```
./mvnw spring-boot:run
```


### Using the maven wrapper to package app on windows ###
```
mvnw clean install
```

### Using maven wrapper to run spring-boot on windows ###

```
mvnw spring-boot:run
```

# Running the application using Gradle

### Using gradle wrapper to run spring-boot ###
```
./gradlew bootRun
```

### Using gradle wrapper to package the app ###
```
./gradlew bootJar
```


# Format checks
````
./mvnw spotless:check

./mvnw spotless:apply
```
