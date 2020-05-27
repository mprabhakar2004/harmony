# harmony_poc
Follow following steps

Step 1: Configure kafka
  - Download apache kafka (https://www.apache.org/dyn/closer.cgi?path=/kafka/2.5.0/kafka_2.12-2.5.0.tgz) 
  - Untar zip to kafka_local
  - $ cd kafka_local
  - $ bin/zookeeper-server-start.sh config/zookeeper.properties
  - $ bin/kafka-server-start.sh config/server.properties
  - $ bin/kafka-topics.sh --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1 --topic UserChangeEvent


Step 2: 
  $ git clone https://github.com/mprabhakar2004/harmony_poc.git
  
Step 2:
  cd to individual directory and run the applcation. e.g.
    $ cd approval
    approval$ gradle bootRun
    
    $ cd changeeventlistener
    changeeventlistener$ gradle bootRun
    
    $cd changeeventproducer
    changeeventproducer$ gradle bootRun
    

Step 4: Import postman collection - https://www.getpostman.com/collections/9bb0f7911a11311a3e44

