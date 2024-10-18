package com.emse.spring.automacorp.hello;

import org.springframework.stereotype.Service;

@Service
public class GreetingServiceImpl implements GreetingService {

    @Override
    public void greet(String name) {
        System.out.println("Hello, " + name + "!");
    }
}
