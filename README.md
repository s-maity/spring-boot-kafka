# spring-boot-kafka

### cloudkarafka 
Kafka cluster on cloud https://www.cloudkarafka.com/ 
Log in with a GitHub account ( I already have a free account) 

### Local Kafka cluster
See https://kafka.apache.org/quickstart (Run in Kraft mode without Zookeeper)

-----------------

Run producer application and hit the end point to publish message 
 [POST] http://localhost:8047/api/v1/event/{message}

Run the consumer application. We can run multiple instances by providing
--server.port=8090 in the program argument. Both the instances will join 
in a consumer group and partitions will be assigned to the consumer instances. 
We can test different options available for  @KafkaListener