# AccountManagement

This is an example spring boot project. It is a simple API performing typical CRUD operations for a User Account Management system.

### Usage and Documentation
When running default properties an in memory DB will be spun up. Any data entered will only persist in memory during the lifespan 
of the process. 
Login using the following credentials:

```username: admin```   
```password: admintest```

Once up and running a [Swagger-UI interface](http://localhost:8080/api/swagger-ui.html) documents and describes the 
operations in detail. You are also able to log in using the credentials above and perform operations.   

###TODO'S
1. Dockerfile to containerize
2. travis-ci for CI/CD

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
 
 