# YouTap Solution

This solution was implemented with the following technologies/frameworks and certain considerations highlighted below were made in coming up with the solution.

### Key Technologies

* Java 17
* Spring Boot (2.7.3)
* Maven
* Okhttp Rest Client
* JUnit 5, Mockito (Unit Tests)
* Mockserver (Integration Testing Mocks)
* Project Lombok
* Project Lombok
* OpenAPI 3
* Spring Boot Actuator

Among others usual technologies/frameworks.

### Points to note

* This solution was designed in such a way that it takes in two request parameters on the `GET`: `/getuserdetails` endpoint exposed, and these two request parameters are `userId` and `username`.
* These 2 request parameters are independently optional, but they can't be both missing. 
* The `userId` request parameter takes higher priority if both are supplied and the `username` won't be considered. The main reason for this was to avoid a scenario where the `userId` would be correct, and the `username` would be wrong, hence doing a search with both parameters would result in a record not being found. However, there are so many ways to do it, that is we could search using both parameters in the first instance, if not record found then search using the userId only, if not found then search using the username only to exhaust all the combinations but in this instance since the requirement didn't clearly spell it out, this is how it was designed.
* The decision to go with request parameters is because it's not so REST like, in terms of good practices, though possible to have a request body but if the requirement was not strict on exposing the endpoint as a `GET` then a POST would have been considered considering that the `userId` and `username` might be sensitive data that might need to be encrypted when the payload is sent over the network to the server. However, for the purpose of the requirement, I settled on a `GET` with request parameters. 
* Responses differ based on whether a resource was found for the supplied `userId` or `username`.

For cases where a user's record has been found, the response structure is as below:

HTTP:200:OK

```json
{
  "user": {
    "id": 1,
    "email": "Sincere@april.biz",
    "phone": "1-770-736-8031 x56442"
  }
}
```
However, for scenarios where a record is not found, the response is like:

HTTP:200:OK

```json
{
  "metaData": {
    "code": 404,
    "message": "User not found"
  },
  "user": {
    "id": -1
  }
}
```

With the `metaData` object providing more useful details that the client can use.

For cases were both parameters are missing, the following response is returned:

HTTP:200:OK

```json
{
    "metaData": {
        "code": 400,
        "message": "Both UserID and Username cannot be null"
    }
}
```

Other scenarios, are also catered for as well. You can reference the attached Postman collection or play around with the requests to generate different error scenarios.

### How to run it.

* Make sure that [Maven](https://maven.apache.org/) is installed on your environment and you have at least Java JDK 17 installed.
* Navigate into the root folder of the project where the `pom.xml` file resides and issue the following command `mvn spring-boot:run`
* To verify that the application has successfully started access the following health check endpoint `http://localhost:8090/actuator/health` and if the service is up you should get the following response:

```json
{"status":"UP"}
```

### How to test.

* If you navigate under the `resources/collections` folder you will see a Postman collection (`YouTap.postman_collection.json`) with saved `request/reponses` that you can reference.
* Before submitting your requests make sure the application is running as specified above and you can issue a request as below using any http client of your choice for example Postman/Insomnia/SoapUI/Swagger-UI/Web Browser/Intellij's HTTP Client e.t.c

`http://localhost:8090/getusercontacts?user=1&username=Bret`

You should get an HTTP:200:OK response like this:

```json
{
  "user": {
    "id": 1,
    "email": "Sincere@april.biz",
    "phone": "1-770-736-8031 x56442"
  }
}
```

For scenarios where the supplied userId or username does not match any records from Typicode's API, then you should get response as below:

HTTP:200:OK

```json
{
  "metaData": {
    "code": 404,
    "message": "User not found"
  },
  "user": {
    "id": -1
  }
}
```
The `metaData` object is used to pass in additional information that the client might want to use to perform specific business logic on their end.

### Running Unit and Integration tests.

Unit and Integration tests exists under the `test` directory to ensure good code quality.

* To run unit test just issue the command `mvn test` or `mvn clean install` 
* To run integration tests, an `it-test` profile has been created under in the `pom.xml` file and in order to run the integration tests issue the command `mvn verify -Pit-test` or `mvn clean install -Pit-test`.

### Accessing the OpenAPI documentation.

* Make sure that the application is running as specified above.
* Access this url `http://localhost:8090/swagger-ui.html` on the browser to see the API documentation.