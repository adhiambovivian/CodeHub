# CodeHub
export PATH=$PATH:/opt/apache-maven/bin

# install rabbitmq
docker run -d --hostname my-rabbit --name rammitmq -p 15672:15672 -p 5672:5672 rabbitmq:3-management