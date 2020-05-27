# harmony_poc


##Step 1: Configure kafka
  - Download apache [kafka](https://www.apache.org/dyn/closer.cgi?path=/kafka/2.5.0/kafka_2.12-2.5.0.tgz) 
  - Untar zip to kafka_local
  - `$ cd kafka_local` 
  - `$ bin/zookeeper-server-start.sh config/zookeeper.properties`
  - `$ bin/kafka-server-start.sh config/server.properties`
  - `$bin/kafka-topics.sh --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1 --topic UserChangeEvent`


##Step 2: 
  `$ git clone https://github.com/mprabhakar2004/harmony_poc.git`
  
##Step 3:
  cd to individual directory and run the application. e.g.-
  
    $ cd approval
    $ gradle bootRun
    
    $ cd changeeventlistener
    $ gradle bootRun
    
    $cd changeeventproducer
    $ gradle bootRun
    

##Step 4: Import postman collection - https://www.getpostman.com/collections/9bb0f7911a11311a3e44

or use Curl : 

###Produce single event : 

`$ curl -H "Content-Type: application/json" -d '{                                                                                            
           "changeRequestId": "2",
           "changeEventType": "OFFBOARD",
           "userAdName": "manikuma",
           "userEmail": "manikumar@linkedin.com",
           "userOrg":"R&D",
           "userDepartment":"EPE",
           "userDesignation": "Software Engineer",
           "flowEnum": "APPROVAL_REQUIRED"
   }' http://localhost:9001/changeevent`
   
 ### Produce Bulk event
 `$ curl -H "Content-Type: application/json"
     -d '[
         	{
         		"changeRequestId": "1",
         		"changeEventType": "ONBOARD",
         		"userAdName": "manikuma",
         		"userEmail": "manikumar@linkedin.com",
         		"userOrg":"R&D",
         		"userDepartment":"EPE",
         		"userDesignation": "Software Engineer",
         		"flowEnum": "APPROVAL_REQUIRED"
         	},
         	{
         		"changeRequestId": "2",
         		"changeEventType": "OFFBOARD",
         		"userAdName": "shrkumar",
         		"userEmail": "shrkumar@linkedin.com",
         		"userOrg":"R&D",
         		"userDepartment":"EPE",
         		"userDesignation": "Staff Software Engineer",
         		"flowEnum": "APPROVAL_REQUIRED"
         	},
         	{
         		"changeRequestId": "3",
         		"changeEventType": "ONBOARD",
         		"userAdName": "tarmitha",
         		"userEmail": "tarmitha@linkedin.com",
         		"userOrg":"R&D",
         		"userDepartment":"EPE",
         		"userDesignation": "Software Engineer",
         		"flowEnum": "APPROVAL_NOT_REQUIRED"
         	}
         ]' http://localhost:9001/changeevent`
         
 ### Check request queue
 `$ curl http://localhost:9000/approvals`
 
 ### Approve change request
 `$ curl http://localhost:9000/approvals/2?action=APPROVED`
 
 