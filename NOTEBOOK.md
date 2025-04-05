# NOTEBOOK

This document contains some notes on topics that I thought it was necessary to remember, or something that I had to study further to implement in this application.

## Maven commands

To execute the project run the following command:

```bash
./mvnw spring-boot:run
```

To install, or reinstall, dependencies using `maven` execute the following:

```bash
./mvnw install
```

### References

* [java - How to start up spring-boot application via command line? - Stack Overflow](https://stackoverflow.com/questions/47835901/how-to-start-up-spring-boot-application-via-command-line);
* [Maven: Command to update repository after adding dependency to POM - Stack Overflow](https://stackoverflow.com/questions/8563960/maven-command-to-update-repository-after-adding-dependency-to-pom)

## Building a RESTful api

```java
package com.example.restservice;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }
}
```

The `@RestController` annotation above the class indicates that this resource is supposed to send domain objects instead of a view.

Inside the controller class we should have functions that will respond to Http requests. In order to do that, every function is mapped to a different path and Http Verb. In the example code, the function `greeting()` should respond the resquest to `GET /greeting`. This is indicated by using the `@GetMapping(path)` annotation over the function and passing the path as argument. There are other mapping annotations other Http verbs like `@PostMapping(path)`, `@PatchMapping(path)`, `@PutMapping(path)`, and `@DeleteMapping(path)`.

To receive other information from the request, like, params or body it is possible to use annotations on the parameters of the controller's function. The `@RequestParam(value, default)` is used to indicate a key from the query string in the URL. Along with that, we can also take variables from the path with `@PathVariable` annotation as follow:

```java
package com.example.restservice;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/greeting/{name}")
    public Greeting greeting(@PathVariable String name) {
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }
}
```

After running the function, the return object must be converted to JSON. But, because of [Jackson2](https://github.com/FasterXML/jackson) this conversion don't have to be manually. Spring’s `MappingJackson2HttpMessageConverter` is automatically chosen to convert the Greeting instance to JSON.

### References

* [Getting Started | Building a RESTful Web Service](https://spring.io/guides/gs/rest-service), accessed on April 3, 2025;
* [Mapping Requests :: Spring Framework](https://docs.spring.io/spring-framework/reference/web/webmvc/mvc-controller/ann-requestmapping.html), accessed on April 3, 2025;

## Throwing custom exceptions in Spring REST api

In this application I used custom exceptions to handle business logic exceptions in the service layer. For example:

```java
package dev.gmorikawa.toshokan.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Username is already registered")
public class UsernameNotAvailableException extends RuntimeException { }

```

The class above represents a exception that occurs in case the given username is already registered in the system. To ensure that Spring will handle it in a more elegant way I used the `@ResponseStatus` annotation over the custom exception class by passing two parameters: first the `code`, which represents the [Http Status](https://developer.mozilla.org/en-US/docs/Web/HTTP/Reference/Status), and the `reason`, which represents the message that will be sent in the response object.

The problem with this approach is that we don't have much control on how the response body should constructed. To have more control over the response body when handling exceptions is by using a `@ControllerAdvice` annotated class:

```java
package dev.gmorikawa.toshokan;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import dev.gmorikawa.toshokan.user.exception.EmailNotAvailableException;
import dev.gmorikawa.toshokan.user.exception.UsernameNotAvailableException;

@ControllerAdvice
public class AppExceptionHandler 
  extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ 
        EmailNotAvailableException.class,
        UsernameNotAvailableException.class
    })
    ResponseEntity<ExceptionResponseBody> handleConflict(RuntimeException ex, WebRequest request) {
        ExceptionResponseBody responseBody = new ExceptionResponseBody(HttpStatus.CONFLICT, ex.getMessage());

        return new ResponseEntity<ExceptionResponseBody>(responseBody, HttpStatus.CONFLICT);
    }
}
```

In this code, the function is handling in case one of the exceptions listed on `@ExceptionHandler()` annotation are thrown. The `ExceptionResponseBody` is a custom class that I created to represent the structure of response body. It should be serializable

### References

* [Exception Handling in Spring MVC](https://spring.io/blog/2013/11/01/exception-handling-in-spring-mvc), accessed on April 4, 2025;
* [HTTP response status codes - HTTP | MDN](https://developer.mozilla.org/en-US/docs/Web/HTTP/Reference/Status), accessed on April 4, 2025;
* [RuntimeException (Java Platform SE 8 )](https://docs.oracle.com/javase/8/docs/api/java/lang/RuntimeException.html), accessed on April 4, 2025;
* [ResponseStatus (Spring Framework 6.2.5 API)](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/bind/annotation/ResponseStatus.html), accessed on April 4, 2025;

## Mapping entities relationships in Spring

To map a unidirectional _many-to-one_ we can use the `@ManyToOne` with `@JoinColumn` annotation. In the below example code, I am mapping the property `publisher` from Book to the entity Publisher. The `@JoinColumn` indicates that when data is read it should make a join to bring the entire entity together:

```java
package dev.gmorikawa.toshokan.document;

import dev.gmorikawa.toshokan.publisher.Publisher;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "books")
public class Book extends Document {
    @JoinColumn(name = "publisherId")
    @ManyToOne
    private Publisher publisher;

    @Column(unique = true)
    private String isbn;
}
```

### References

* [Running the Persistence Examples :: Jakarta EE Tutorial :: Jakarta EE Documentation](https://jakarta.ee/learn/docs/jakartaee-tutorial/current/persist/persistence-basicexamples/persistence-basicexamples.html#_entity_relationships_in_the_order_application), accessed on April 6, 2025;
