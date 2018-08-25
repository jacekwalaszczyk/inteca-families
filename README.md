# Inteca Families

**Inteca Familes** is a distributed application based on Angular, Spring Boot and MySql.
It has been written as a evaluation task for a Java Developer position.
The purpose of this application is to serve as an archive of families.

## Application architecture

The applicatin consists of 3 layers deployed as Docker conteners:
* **The User Interface layer** - named **_client_** and done using the web framework [Angular](https://angular.io/guide/quickstart) version 6.
* **The REST Services Server layer** - named **_server_** has been made using the [Spring Boot](http://spring.io/projects/spring-boot) version 2.0.4 framework for **Spring** applications and [Java](https://www.java.com) version 8 update 181 as a programming language.
* **The Database layer** - named **_db_** based on the [MySQL](https://www.mysql.com/) version 8.0 open source database.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine.

### Prerequisites

* To install the software you need a [Docker](https://github.com/docker/docker-ce/releases/tag/v18.06.0-ce).
* The source code and configuration files are stored on [GitHub](https://github.com/jacekwalaszczyk/inteca-families). You need a Git client software on your machine, for example a [Git CLI](https://git-scm.com/book/en/v2/Getting-Started-The-Command-Line).

### Getting the sources

The first step for a installation is to fetch the source code from [GitHub](https://github.com).
To do this you should open the commang line window and execute the following Git command:

```
git clone https://github.com/jacekwalaszczyk/inteca-families.git
```
After successful execution you should have a **_inteca-families_** directory created. Inside this directory you should see 3 subdirectories:
* client
* server
* db

and a file **_docker-compose.yml_** which contains the commands for [Docker](https://github.com/docker/docker-ce/releases/tag/v18.06.0-ce). These 3 subdirectories contain 3 layers of our application and are deployed as separate Docker containers.

### Installing

To install the application you just need to open a command line window, go to the **_inteca-families_** directory and execute the following [Docker](https://github.com/docker/docker-ce/releases/tag/v18.06.0-ce) command:

```
docker-compose build
```

## Starting up

To start the application run the following command:

```
docker-compose up
```

If you started the application earlier, you may get an error message like this:

```
ERROR: for inteca-families_nginx_1  Cannot start service nginx: driver failed programming external connectivity on endpoint inteca-families_nginx_1 (2e4a64c48a6c7d2d7fa4d168dfea863d0106afc88ecb4e813ef0d82fd23eaf89): Error starting userland proxy: mkdir /port/tcp:0.0.0.0:80:tcp:172.21.0.4:80: input/output error
```

This is caused by busy/blocked 80 port which is used for communication from the web browser to the web server (I used a [NGINX](https://nginx.org/en/download.html) web server as a embedded web server inside the container). To free this port you should shut down all processes opened by Docker:

```
docker-compose down
```
and additionally, restart the Docker itself - the restart option is in the right mouse button menu of Docker's process icon. After that, run the **_docker-compose up_** command again.
When everything went right you should get the following message on the command line window:

```
[System] [MY-010931] [Server] /usr/sbin/mysqld: ready for connections. Version: '8.0.12'  socket: '/var/run/mysqld/mysqld.sock'  port: 3306  MySQL Community Server - GPL.
```
This message means that the application is started and waiting for the requests.

## Running the families archive

To run the application you just need to start a web browser and go to the following address:

```
localhost
```

You should have been redirected to the _http://localhost/menu_ address and see the main page of application.

## Closing the application

To close the application you should stop all processes started by Docker. This will be done when you press _Ctrl+C_ keys inside the command line window. You should see following messages:

```
Stopping inteca-families_nginx_1  ... done
Stopping inteca-families_spring_1 ... done
Stopping inteca-families_db_1     ... done
```
To stop all processes and delete all Docker containers start the command:

```
docker-compose down
```


## Author

* **Jacek Walaszczyk**


## License

This project is licensed under the MIT License - see the **LICENSE** file for details
