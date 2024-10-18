package com.emse.spring.automacorp;

import com.emse.spring.automacorp.hello.GreetingService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.CommandLineRunner;

@Configuration // (1) Marks this class as a configuration bean
public class AutomacorpApplicationConfig {

    @Bean // (2) Tells Spring that this method returns a new Bean
    public CommandLineRunner greetingCommandLine(GreetingService greetingService) { // (3) Inject GreetingService
        return args -> {
            // (4) Call the greet method on the injected GreetingService to output the message at startup
            greetingService.greet("Spring");
        };
    }
}
