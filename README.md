# Bookstore website with a Microservices Architecture

This repository shows the development of a bookstore website using the microservices architecture.
To implement this architecture, I used:
* the latest version of Java, Java 11;
* the latest version of Spring Boot, Spring Boot 2.3.5;
* Docker to package the application;
* and Kubernetes to manage the microservices.

This repository is related to a playlist video: https://www.youtube.com/playlist?list=PLab_if3UBk9-BjDP7Yh4uiy8z0pEd14Tp


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


