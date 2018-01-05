# poc-on-orchestration-and-choreography

Proof of Concept on orchestration and choreography patterns to model business processes based on the example posted in this very inspirational article ["orchestration and choreography BPM design patterns"](http://plexiti.com/en/blog/2017/03/microservices-orchestration-or-choreography/)

## Main concepts
* [orchestration and choreography BPM design patterns](http://plexiti.com/en/blog/2017/03/microservices-orchestration-or-choreography/)
* [Domain Driven Design](https://airbrake.io/blog/software-design/domain-driven-design)
* [Saga, a.k.a. Event Driven Architecture](http://microservices.io/patterns/data/saga.html)
* [Command Query Responsibility Segregation](https://martinfowler.com/bliki/CQRS.html)
* [Microservices](https://martinfowler.com/articles/microservices.html)

## Context Map
The [Context Map](https://www.infoq.com/articles/ddd-contextmapping) for the example described in [this article](http://plexiti.com/en/blog/2017/03/microservices-orchestration-or-choreography/) is:

* Finance
    * Payments
* Sales
    * Order
    * Checkout
* Products
    * Inventory 
    * Shipment

Each secod level element is actually implemented as a microservice.

## Technology stack
* Spring Boot (Microservices)
* Camunda BPM (Busines Process Orchestation)
* Apache Kafka (Business Process Choreography)

## Run the PoC
Pre-requisites:
* [install](https://git-scm.com/book/en/v2/Getting-Started-Installing-Git) a git client
* [install](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) java 1.8+
* [install](https://maven.apache.org/install.html) Apache Maven 3+
* [install](https://kafka.apache.org/quickstart) Apache Kafka 

Clone the repo

```
git clone https://github.com/jantoniucci/poc-on-orchestration-and-choreography.git
cd poc-on-orchestration-and-choreography
```

Run a full build

```
mvn install
```

At your Kafka's home directory, start zookeeper

```
bin/zookeeper-server-start.sh config/zookeeper.properties
```

start kafka

```
bin/kafka-server-start.sh config/server.properties
```

create a topic *"retail"*

```
bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic retail
```

Check existing topics

```
bin/kafka-topics.sh --list --zookeeper localhost:2181
```

Start the microservices components one by one
   
```
mvn -f checkout exec:java
mvn -f inventory exec:java
mvn -f monitor exec:java
mvn -f order exec:java
mvn -f payment exec:java
mvn -f shipping exec:java
```

Now you can place an order via [http://localhost:8090](http://localhost:8090)
You can inspect Order via [http://localhost:8091](http://localhost:8091)
You can inspect Payment via [http://localhost:8092](http://localhost:8092)
You can inspect all events going on via [http://localhost:8095](http://localhost:8095)
