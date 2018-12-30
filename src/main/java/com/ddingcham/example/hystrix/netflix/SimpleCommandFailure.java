package com.ddingcham.example.hystrix.netflix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

public class SimpleCommandFailure extends HystrixCommand<String> {
    static final String COMMAND_ID = "SimpleCommand";
    private final String name;

    public SimpleCommandFailure(String name) {
        super(HystrixCommandGroupKey.Factory.asKey(COMMAND_ID));
        this.name = name;
    }

    @Override
    protected String getFallback() {
        return COMMAND_ID + " : " + name;
    }

    @Override
    protected String run() {
        throw new RuntimeException("this command always fails!");
    }
}
