## Using Dependency Injection

### Other cases

#### Q.1: What happens if you comment the @Component / @Service annotation on your ConsoleGreetingService?

**Answer:**
If you comment out the `@Service` annotation, the `ConsoleGreetingService` will no longer be registered as a Spring Bean. As a result, Spring will not be able to inject it where required, leading to a `NoSuchBeanDefinitionException` when trying to use this class in the application.

#### Q.3: Finally, try the following - what happens and why?

**Answer:**
This issue occurs because of a **circular dependency** between `ConsoleGreetingService` and `CycleService`. Both services depend on each other, causing a loop that Spring cannot resolve. When `ConsoleGreetingService` is initialized, it requires `CycleService`, and vice versa. Circular dependencies are discouraged and, by default, prohibited in Spring Boot unless explicitly allowed by setting `spring.main.allow-circular-references` to `true`.
