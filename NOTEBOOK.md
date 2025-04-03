# NOTEBOOK

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
