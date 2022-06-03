# Bookstore website with a Microservices Architecture

This repository shows the development of a bookstore website using the microservices architecture.
To implement this architecture, I used:
* the latest version of Java, Java 11;
* the latest version of Spring Boot, Spring Boot 2.3.5;
* Docker to package the application;
* and Kubernetes to manage the microservices.

This repository is related to a playlist video: https://www.youtube.com/playlist?list=PLab_if3UBk9-BjDP7Yh4uiy8z0pEd14Tp

### Database on localhost

To create the database on localhost, run the following commands:
´´´
cd database-image
docker build . -t microservices-bookstore-db
docker run -d -e POSTGRES_HOST_AUTH_METHOD=trust -e POSTGRES_USER=ser -e POSTGRES_PASSWORD=ser -e POSTGRES_DB=bookstore -p 5432:5432 microservices-bookstore-db
´´´

### Apache Kafka on localhost

The service-users and service-mails need Apache Kafka to be running. Download it from https://kafka.apache.org/downloads
and run it as indicated at https://kafka.apache.org/quickstart.

## Chapter 1

In this first chapter, I've created a dummy application with Spring Boot. This application will only have
an endpoint which returns a string "Hello World". From this application, I've created a docker
container to have the packaged application.

I've used Minikube to have a set of virtual machines used for the Kubernetes cluster. Minikube will have
all the features needed to deploy my dummy application.

Nevertheless, I must build my Docker image in the Minikube cluster to allow Minikube retrieve it when
deploying the Kubernetes configuration.

With Kubernetes, I've declared a deployment for my packaged application, and pushed to the Minikube cluster.
From the deployment, I've added a Kubernetes service to have my dummy application available publicly.

And finally, exposed the dummy service to my localhost from Minikube. This way, I can request it.


## Chapter 2


In the second chapter, I've used Kubernetes configuration to implement the Service Discovery pattern. For that,
I've created a service configuration for each microservice, specifying its type to `ClusterIp` which is the
default type. This way, all the pods of a deployment will have a unique IP. And an internal load balancer with
a round robin strategy will balance the requests to the pods.

Then, I've used the DNS record names created by Kubernetes DNS service to reach each service inside my cluster.
I've used those DNS records to indicate at each microservice where to request the needed one.

And to perform the HTTP requests, I've added the Retrofit library where I just create interfaces to communicate
to each configured DNS easily.


## Chapter 2.2

In this second part of the second chapter, I've used the library Spring Cloud Netflix Eureka to implement the
Service Discovery pattern. The Eureka library is divided in two parts: the server and the clients. The server
is the one dedicated to have a list with all the available microservices in the architecture. And the clients
will register itselves into the server. Even if multiple instances of the same microservice register into the
server, it won't cause a problem, the server will use a round robin strategy to redirect the traffic to both
instances.

To request the microservices, I've used the Feign client library. The Feign library is similar basically Retrofit
adapted to Eureka. The Eureka client will tell the Feign client the address of the target microservice to
request. This way, I only need the name of the microservice and not its address to request it.


## Chapter 3

The third chapter is dedicated to the API Gateway. This time, I show how to use Spring Cloud Gateway to redirect
the traffic to some inner microservices. The previous backend-user microservice dissapeard as itself. The
requests inside my microservice architecture will be done by the private microservices now. Each microservice has
the responsability to build a complete response before returning it to the user.

The first part is dedicated to use this library using only the configuration file. Indicating the rules to redirect
each URL given its path to a dedicated microservice. The API Gateway will use the Service Discovery to match
the inner microservices with their name.

In the second part, I want to implement a more complex Gateway using the authentication. I want that before redirecting
the request to the inner services, I ensure that the request has the correct headers with the authenticated information.
This time, I must use the Java code to implement this pattern. Before redirecting the request to the target microservice
I make a request to the authentication service to ensure the correct headers, then the request continue.


## Chapter 4

This fourth chapter handles the distributed configuration with Spring Cloud Config. Having a distributed configuration
ease the way an application is loaded and started. As the configurations are fetch from a Git repository, I ensure
they are always up to date.

The second part of the video, handles the distributed configuration against two sources: Vault for the username and passwords
to connect to the database, and Git for the rest. This means that I must set a priority order in the configuration sources.
Thus, Vault requires some additional configuraitons, as the authentication via token.



## Chapter 5

In this fifth chapter, I show how to use Apache Kafka as a message broker between to microservices. I try to separate the
need to send an email from sending the email itself. When creating a user in the database, I want an email to be sent
as confirmation of the user's account. I want the microservice service-users to create the user's account in the database
and the publish a message into Apache Kafka. Another microservice, service-mails, will be listenning to Apache Kafka for
new messages to create and send an email.

For this purpose, the publisher, service-users, will be configured to send messages to Apache Kafka. And the consumer,
service-mails, will be configured to read messages from Apache Kafka. Both microservices will only need the Spring dependency
of Apache Kafka. Both microservice will also need to configure the connection to Apache Kafka. And then, the publisher will
need to configure the template to write messages, and the consumer will need to configure the topic and consumer groups to
read messages.

### Kafka on localhost

To run Apache Kafka on localhost, follow the instructions at https://kafka.apache.org/quickstart.


## Chapter 6

In this new chapter, I explain the Circuit Breaker pattern. I implement it with both Hystrix and Resilience4J.

The Circuit Breaker pattern allows to return a default value to HTTP requests when the subsequent service is unavailable.
As the subsequent service is unavailable, trying to request it may fall into timeouts or unhandled exceptions. The Circuit
Breaker will be notified by this behavior and switch to a default value. Meanwhile, it will check until the service
is back.


## Chapter 7

In this chapter, I build a new microservice which will handle some batch jobs with Spring Batch. Spring Batch can handle
long running batches within splitted transactions. It will split the data in chunks and handle each chunk separatly.

There are two ways to run batch jobs, with step and with tasklets. With steps, I can configure a Reader, a Processor and
a Writer. All are optional. The Reader will just read some information (from a database, file or other) and store it in 
the step context. Then, the processor will read from the context to perform the necessary modifications and store again 
the results in the context. Finally, the Wirter will read the modified data and write it in the desired destination 
(datasource, file or other).


## Chapter 8

In this chapter I use Sleuth to trace the requests between the microservices. Sleuth will allow to identify the requests
from the source microservice to the target microservices. Each log will be annotated with a Trace ID and a Span ID. The
Trace ID will be the same for all the logs created in all the microservices from the same request. The Span ID will maintain
the value within a single microservice for a single request. With this strategy, I can easily identify all the logs generated
in all the microservices from a single user request.

On the other side, Zipkin will group all the information about the Traces and Spans. This way I can know the time consuming
by each step for a single request. Zipkin is a trace application to allow identify slow processes in a microservices architecture.
 

