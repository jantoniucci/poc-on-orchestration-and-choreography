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
```
