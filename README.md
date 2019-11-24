### **Creating a cloud using docker swarm:**

#### **Docker swarm:**

Is to a cluster of Dockers we can use to create docker instances.

* Initialize: `docker swarm init` 
* Get details of docker system: `docker info`
Docker service: Is a set of instances of an existing Docker image that we can manage.
* Create a service: `docker service create --replicas 1 --name <name> <image> <command>`
    * **replicas** are number of tasks
* List services: `docker service ls`
* Log service: `docker service logs -f <name>`
* Remove a service: `docker service rm <name>`
* Create a registry: `docker service create --registry --publish <port_docker>:<port_machine> registry`
* Check the catalog: `http://localhost:<port_machine>/v2/_catalog`
* Add the service in the swarm: `docker service create --name hello-service --publish 8080:8080 localhost:5000/hello`

Workaround when registering a service fails for any reason:

* Add the following entries to the **private/etc/hosts** file:

	`127.0.0.1	      localhost registry.me`
* Create a new registry: `docker run -d -p 5000:5000 --restart=always --name registry registry:2`
* Package the micro service: mvn package
* Build the docker image inside the project folder: `docker build . -t <project_name>`
* Tag the docker image to fit in the registry domain: `docker tag <project_name> registry.me:5000/<project_name>`
* Push the docker image: `docker push registry.me:5000/<project_name>`
* Add the service to run in the swarm: `docker service create --name hello-service --publish 8080:8080 localhost:5000/hello`

Note: the good thing is that localhost can be used anyway because of the entry in `etc/hosts`.
Note2: If registry goes nuts: `lsof -P | grep ':5000' | awk '{print $2}' | xargs kill -9`

#### **Scaling microservice:**

* Scale by n replicas: `docker service scale <service_name>=n`
* To stop all replicas: `docker service scale <service_name>=0`
* To list all the instances associated in a service: `docker service ps <service_name>`
* To delete all the instances associated in a service: `docker service rm <service_name>`