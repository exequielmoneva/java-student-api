# Student API

A simple Rest API for registering basic information from students following the Domain-driven design (DDD) approach.


# Table of Contents
1. [Introduction](#Introduction)
2. [Technical stack](#Technical-stack)
3. [Requirements](#Requirements)
4. [How to use](#How-to-use)
5. [ToDos](#ToDos)

# Introduction
I build this simple API as a way for me to review some key concepts from Java and Software Engineering in general. 

# Technical Stack

### Java and Spring Boot
Since my whole professional experience with Java has always been with Spring Boot, I decided to use it for this project.
As for Java, I decided to use the latest version at the moment (17).

### PostgreSQL
Since I have experience using this database, I decided to take advantage of its simplicity. 
I could have used some in-memory database such as H2 but I preferred Postgres for a more professional approach.


# Requirements
- Java JDK 17 or later
- Docker
- PostgreSQL
- Target and .jar file of this project generated (some IDEs can create it by right clickin in the root folder, but you [can also do it manually](https://docs.oracle.com/javase/tutorial/deployment/jar/build.html))

# How to use
Inside the root folder of the project, run the following command

```sh
> docker-compose build
```
<br>
<br>

Then start the project with the following command

```sh
> docker-compose up
```
<br>
<br>

You should be able to check the API's health at:

```
http://localhost:8080
```
<br>
<br>

Now, you should be able to test the API at:
<br>
<br>

```
http://localhost:8080/api/v1/student
```
<br>
<br>

You can also test the documented endpoints without the frontend at
```
localhost:8080/api/v1/docs
```
<br>
<br>

#### If you wish to use Postman, you can find the collection [here](https://github.com/exequielmoneva/java-student-api/tree/master/postman-collections)
<br>
<br>

## API specification

|         Task         |                     URL                     | Method | Response code |       Response       |
|:--------------------:|:-------------------------------------------:|:------:|:-------------:|:--------------------:|
|   Check API health   |                      /                      |  GET   |      200      |      API is up!      |
| request all students |               /api/v1/student               |  GET   |      200      | List of all students |
|  Get single student  |         /api/v1/student/{studentId}         |  GET   |      200      |       Student        |
|   Create a student   |               /api/v1/student               |  POST  |      204      |   Created student    |
|    Update student    | /api/v1/student/update/studentId?email?name |  PUT   |      200      |   Updated student    |
|    Delete student    |     /api/v1/student/delete/{studentId}      |  POST  |      204      |   Student deleted    |

# ToDos
- Unit and integration tests
