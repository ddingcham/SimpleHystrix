package com.ddingcham.example.hystrix.netflix.cache;

import com.netflix.hystrix.HystrixCommand;

public class SimpleCommandCache extends HystrixCommand<Boolean> {
    private final int value;

    public SimpleCommandCache(int value) {
        super(Setter.withGroupKey(CacheCommandContexts.CACHE_COMMAND_KEY));
        this.value = value;
    }

    @Override
    protected String getCacheKey() {
        return String.valueOf(value);
    }

    @Override
    protected Boolean run() {
        return value == 0 || value % 2 == 0;
    }
}