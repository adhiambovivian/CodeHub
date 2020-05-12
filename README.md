# CodeHub

export PATH=$PATH:/opt/apache-maven/bin

Pre-requisites
- Java > version 8
- Rabbitmq
- Postgresql
- redis


# install rabbitmq
docker run -d --hostname my-rabbit --name rammitmq -p 15672:15672 -p 5672:5672 rabbitmq:3-management

