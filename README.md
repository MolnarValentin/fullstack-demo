**docker-demo**

This demo application has 2 purposes:
- To show how could we develop locally with the help of docker-compose and bind volumes
- To show how could we prepare the containers for deployment with the help of Kubernetes (where the containers can be scaled, managed etc..)

Other differences in the app for local and "prod" development:
- for local mongodb container was used for storing data
- for "prod" mongodb atlas was used (cloud database)
- for prod nginx was used for frontend
