package com.example.fluenttest;

import org.khasanof.StateConfigurerAdapter;
import org.khasanof.state.configurer.StateConfigurer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.EnumSet;

@SpringBootApplication
public class FluentTestApplication implements StateConfigurerAdapter<SimpleState> {

    public static void main(String[] args) {
        SpringApplication.run(FluentTestApplication.class, args);
    }

    @Override
    public void configure(StateConfigurer<SimpleState> state) {
        state.initial(SimpleState.DEFAULT)
                .states(EnumSet.allOf(SimpleState.class));
    }
}
