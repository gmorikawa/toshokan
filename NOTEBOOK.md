# NOTEBOOK

This document contains some notes on topics that I thought it was necessary to remember, or something that I had to study further to implement in this application. This is not intended to be a guide.

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

To map a unidirectional _many-to-many_ we can use the `@ManyToMany` annotation to indicate a collection that represents the relationship. For example, in this application I have `Documents` that can have many `Topics`, hence the relationship many-to-many:

```java
public abstract class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String title;

    private Integer year;

    private String authors;

    private String description;

    @JoinColumn()
    @ManyToOne
    private Category category;

    @JoinTable(
        name = "document_topics",
        joinColumns = @JoinColumn(
            name = "document_id",
            referencedColumnName = "id"
        ),
        inverseJoinColumns = @JoinColumn(
            name = "topic_id",
            referencedColumnName = "id"
        )
    )
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Topic> topics;

    public Document() {
        topics = new ArrayList<Topic>();
    }
}
```

```java
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;

    @ManyToMany(mappedBy="topics")
    private Set<Document> documents;
}
```

Since only the document will add topics to it, not the other way around, it is called unidirectional. Because of that, `documents` in `Topic` has a _mappedBy_ parameter to indicate the field that owns the relationship (from where it will be registered).

### References

* [Running the Persistence Examples :: Jakarta EE Tutorial :: Jakarta EE Documentation](https://jakarta.ee/learn/docs/jakartaee-tutorial/current/persist/persistence-basicexamples/persistence-basicexamples.html#_entity_relationships_in_the_order_application), accessed on April 6, 2025;
* [javax.persistence.ManyToMany - JPA Annotation - JPA API Reference](https://www.objectdb.com/api/java/jpa/ManyToMany), accessed on April 6, 2025;

## Modeling inheritance in database

In the database layer there is no out of the box solution to represent the OOP inheritance between entitis. _JPA_ offers some strategies of inheritance, each with pros and cons, and the main issue will be performance. These strategies are:

* __MappedSuperclass__: the parent classes, can’t be entities
* __Single Table__: The entities from different classes with a common ancestor are placed in a single table.
* __Joined Table__: Each class has its table, and querying a subclass entity requires joining the tables.
* __Table per Class__: All the properties of a class are in its table, so no join is required.

For my case, I have a class `Document` that will act as parent class, while I can have, for example, `Book` and `Whitepaper` as children class.

```java
@Entity
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String title;

    private Integer year;

    private String authors;

    private String description;

    @JoinColumn(name = "categoryId")
    @ManyToOne
    private Category category;
}
```

```java
@Entity
public class Book extends Document {
    @JoinColumn(name = "publisherId")
    @ManyToOne
    private Publisher publisher;

    @Column(unique = true)
    private String isbn;
}
```

For this case I decided to go with _Table per Class_ strategy, since I don't have the need to handle Documents generically yet. _Joined Table_ was something that I was imagining at first, but it might generate unnecessary overhead just for a simple listing.

### References

* [Introduction to Jakarta Persistence :: Jakarta EE Tutorial :: Jakarta EE Documentation](https://jakarta.ee/learn/docs/jakartaee-tutorial/current/persist/persistence-intro/persistence-intro.html#_entity_inheritance), access on April 5, 2025;
* [InheritanceType (Jakarta Persistence API documentation)](https://jakarta.ee/specifications/persistence/2.2/apidocs/javax/persistence/inheritancetype), accessed on April 5, 2025;
* [Hibernate Inheritance Mapping | Baeldung](https://www.baeldung.com/hibernate-inheritance), accessed on April 5, 2025;
* [Mastering JPA Inheritance Strategies: Hibernate 6.x JPA 3.x Spring Boot 3.x | by Praveen kumar | Medium](https://medium.com/@iampraveenkumar/mastering-jpa-inheritance-strategies-hibernate-6-x-jpa-3-x-spring-boot-3-x-06eecac1147a), accessed on April 5, 2025;

## Enabling CORS on Spring REST api

```java
@Bean
public WebMvcConfigurer corsConfigurer() {
    return new WebMvcConfigurer() {
        @Override
        public void addCorsMappings(CorsRegistry registry) {
            registry.addMapping("/api/**").allowedOrigins("http://localhost:4200");
        }
    };
}
```

### References

* [Getting Started | Enabling Cross Origin Requests for a RESTful Web Service](https://spring.io/guides/gs/rest-service-cors), accessed on April 8, 2025;
* [Cross-Origin Resource Sharing (CORS) - HTTP | MDN](https://developer.mozilla.org/en-US/docs/Web/HTTP/Guides/CORS), accessed on April 8, 2025;

## REST api token authentication and authorization

### References

* [OAuth 2.0 Resource Server JWT :: Spring Security](https://docs.spring.io/spring-security/reference/servlet/oauth2/resource-server/jwt.html), accessed on April 8, 2025;
* [Spring Boot 3 + Spring Security 6 - JWT Authentication and Authorisation [NEW] [2023] - YouTube](https://www.youtube.com/watch?v=KxqlJblhzfI), accessed on April 10, 2025;
* [jwtk/jjwt: Java JWT: JSON Web Token for Java and Android](https://github.com/jwtk/jjwt#installation), accessed on April 10, 2025;
* [JWT In Springboot 3 | by Bishal Devkota | Medium](https://medium.com/@bishalf98/jwt-in-springboot-3-e50c2ecb0879), accessed on April 10, 2025;
* [Architecture :: Spring Security](https://docs.spring.io/spring-security/reference/servlet/architecture.html), accessed on April 10, 2025;
* [SpringBootでJWT生成と検証 #Java - Qiita](https://qiita.com/Amayz_Ryosuke_Mieda/items/d12c162daa07694ddd5a), accessed on April 21, 2025;

## Implementation of Unit Tests

In Spring, the test files stays in a separated directory from the main source code. Under `src` we should have two directories: `main`, which resides the application source code, and `test`, where all the tests files should be. By running `./mvnw test` (Maven) or `./gradlew test` (Gradle) it should start a mock server and check all the tests.

### References

* [Getting Started | Testing the Web Layer](https://spring.io/guides/gs/testing-web), accessed on April 20, 2025;
* [Testing in Spring Boot | Baeldung](https://www.baeldung.com/spring-boot-testing), accessed on April 20, 2025;
* [Oliver Drotbohm - Why field injection is evil](https://odrotbohm.de/2013/11/why-field-injection-is-evil/), accessed on April 20, 2025;

## Loading environment variables in the application

