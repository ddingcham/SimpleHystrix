package com.ddingcham.example.hystrix.netflix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;


public class SimpleCommandCache extends HystrixCommand<Boolean> {
    static final String COMMAND_ID = "SimpleCommandCache";
    private final int value;

    protected SimpleCommandCache(int value) {
        super(HystrixCommandGroupKey.Factory.asKey(COMMAND_ID));
        this.value = value;
    }

    @Override
    protected String getCacheKey() {
        return String.valueOf(value);
    }

    @Override
    protected Boolean run() throws Exception {
        return value == 0 || value % 2 == 0;
    }
}
