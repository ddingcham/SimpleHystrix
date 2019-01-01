package com.ddingcham.example.hystrix.netflix.cache;

import com.netflix.hystrix.HystrixCommandGroupKey;

public class CacheCommandContexts {
    public static volatile String CACHE_PREFIX = "ValueCached_";
    public static final String CACHE_COMMAND = "SimpleCacheCommand";
    public static final HystrixCommandGroupKey CACHE_COMMAND_KEY = HystrixCommandGroupKey.Factory.asKey(CACHE_COMMAND);

    private CacheCommandContexts() {
    }
}
