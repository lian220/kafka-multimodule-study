# kafka-multimodule-study

### 1. start kafka & zooker
```shell
  $ cd kafka-docker     
  $ docker-compose up -d
```

### 2. start consumer1,2 and producer1,2 application
```shell
```

### 3. send message to producer1
```shell
  - use terminal  
  $ curl -X GET -H "Content-Type: application/json" -d '{"name":"lian"}' http://localhost:6061/message_send
  
  or 
  
  - use postman
  get: http://localhost:6061/message_send
  body: raw, json
  {
    "name": "임도영",
    "age": 13,
    "team": "최강"
  }
```