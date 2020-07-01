# AccountManagement

This is an example spring boot project. It is a simple API performing typical CRUD operations for a User Account Management system.

### Usage and Documentation

To run either: 

* Build with gradle  
   ```./gradlew build```   
   ```java -jar build/libs/AccountManagement-[VERSION].jar```   
 
* Run using docker* [docker image](https://hub.docker.com/r/robertreynisson/accountmanagement) based on the latest commit:  
```docker run -p 8080:8080 robertreynisson/accountmanagement```  
  Docker image is created each time there is a new push to this repo, so it might include 'braking changes'
 
  
API should now be accessible at [```http://localhost:8080```](http://localhost:8080) with a 
[Swagger-UI interface](http://localhost:8080/api/swagger-ui.html) documenting the API and describing the 
operations in detail. You are also able to log in using _default user_ and perform operations.

### Default User
When running the default properties an in memory DB will be spun up, generating a default user:
```username: admin```   
```password: admintest```

The database is functional and will store entered data with memory limitation. Any data entered will only persist in memory during the lifespan of the process. 

   

###Libraries and notes
* ```org.springframework.boot v2.3.1``` 
   * ```spring-boot-starter-data-jpa``` Wrapper for data access and ORM libraries
   * ```spring-boot-starter-data-rest```     Exposing and handling controllers to HTTP requests
   * ```spring-boot-starter-data-security``` Wrapper for authentication libraries
   * ```spring-boot-starter-test```Wrapper for testing and mocking 
*  ```com.h2database:h2``` In-Memory Database implementation (for testing)
* ```org.springdoc:springdoc-openapi-ui:1.4.2``` Generating OpenApi document from annotations and a Swagger-UI
 
IntelliJ's http reqest library was used for creating rudimentary integration tests against a running instance
The project uses gradle for compilation.

Travis CI takes care of automated build and pushing a docker image to a repo
 
 