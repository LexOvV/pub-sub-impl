TASK
=======

# Overview:

To make 2 services(spring-boot), which do the following tasks:

### Service 1:

On getting `HTTP-GET` request(any endpoint)
- Forms a byte array, consisting of 200-kilobyte random data
- Puts the array into Redis

### Service 2:
- Takes the array from redis
- Forms a digital signature depending on array data using algorithm ECDSA
- Puts the digital signature in Redis

### Service 1:
- Takes the digital signature from Redis
- Checks if it is correct
- Returns the result of checking in answer for `HTTP GET` request

## Additional terms:
- After checking the digital signature there is no need to contain data in Redis anymore
- Keys and signatures can be fixed-value objects, but there still should be a function for their generation
- Response should contain a correct **JSON-object**, even if an exception was thrown

Prerequisites
-----------

* Installed Java JDK 17
* Installed Maven
* Installed Docker
* Installed Postman (Optional. It needs for sending requests and get responses)
* Installed Git (Optional, to clone from repository)
* Some text editor for editing configuration files (Optional)

How to run application locally through a terminal
------------

1. Clone the project from a repository from GitHub \
With SSH\
   [git@github.com:mikaelk14/test-task-repository.git](git@github.com:mikaelk14/test-task-repository.git) \
or with HTTPS\
[https://github.com/mikaelk14/test-task-repository.git](https://github.com/mikaelk14/test-task-repository.git)
2. Open a terminal ad ``cd`` to the main folder - it's a parent folder for models
3. Go to required model's folder and run a maven script ``mvn sprin-boot:run``
4. Send a ``GET`` request to `localhost:8080/api/v1/message` and get response from

How to run application locally through a docker-compose
------------
1. Clone the project from a repository from GitHub \
   With SSH\
   [git@github.com:mikaelk14/test-task-repository.git](git@github.com:mikaelk14/test-task-repository.git) \
   or with HTTPS\
   [https://github.com/mikaelk14/test-task-repository.git](https://github.com/mikaelk14/test-task-repository.git)
2. Open a terminal ad ``cd`` to the main folder - it's a parent folder for models
3. Go to required model's folder and run a maven script ``docker-compose up --build --detach``.\
You could use ``--force-recreate`` if needed
4. Send a ``GET`` request to `localhost:8080/api/v1/message` and get response from
5. 