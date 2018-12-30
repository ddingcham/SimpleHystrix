package com.ddingcham.example.hystrix.netflix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

public class SimpleCommand extends HystrixCommand<String> {
    static final String COMMAND_ID = "SimpleCommand";
    private final String name;

    public SimpleCommand(String name) {
        super(HystrixCommandGroupKey.Factory.asKey(COMMAND_ID));
        this.name = name;
    }

    @Override
    protected String run() {
        return COMMAND_ID + " : " + name;
    }
}
