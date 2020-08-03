# CodeHub

export PATH=$PATH:/opt/apache-maven/bin

Pre-requisites
- Java > version 8
- Rabbitmq
- Postgresql
- redis
- solr
- Mongodb
- mySQL

**install rabbitmq**

```docker run -d --hostname my-rabbit --name rammitmq -p 15672:15672 -p 5672:5672 rabbitmq:3-management```
- use default rabbitmq container credentials; guest

**install solr**
```docker run -p 8983:8983 -t solr```

```docker exec -it --user=solr <container-id> bin/solr create_core -c participants```

**Install mysql**
```docker run --name mysql -p 3306:3306 -e MYSQL_ROOT_PASSWORD=your-password -d mysql:latest```

**To access mysql terminal**
```docker exec -it mysql bash```
```mysql -u root -p```
```CREATE USER 'your-username'@'localhost' IDENTIFIED BY 'your-password';```
```GRANT ALL PRIVILEGES ON * . * TO 'your-username'@'localhost';```
```FLUSH PRIVILEGES;```


**Running the application**

```mvn spring-boot:run -Drun.jvmArguments='-Dserver.port=7090'```
