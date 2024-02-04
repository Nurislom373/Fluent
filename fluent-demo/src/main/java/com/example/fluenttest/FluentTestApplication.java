package com.example.fluenttest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FluentTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(FluentTestApplication.class, args);
    }

//    @Override
//    public void configure(StateConfigurer<SimpleState> state) {
//        state.initial(SimpleState.START)
//                .states(EnumSet.allOf(SimpleState.class));
//    }

}
