package com.esprit.springbootamazons3.post;

import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class PostViewNameBuilder implements Function<String, String> {
    @Override
    public String apply(String userTel) {
        return new StringBuilder(" POST_VIEW").append(userTel).toString();
    }
}
