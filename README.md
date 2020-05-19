# CodeHub

export PATH=$PATH:/opt/apache-maven/bin

Pre-requisites
- Java > version 8
- Rabbitmq
- Postgresql
- redis
- solr
- Mongodb


# install rabbitmq
docker run -d --hostname my-rabbit --name rammitmq -p 15672:15672 -p 5672:5672 rabbitmq:3-management
- use default rabbitmq container credentials; guest

#install solr
docker run -p 8983:8983 -t solr

docker exec -it --user=solr <container-id> bin/solr create_core -c participants
