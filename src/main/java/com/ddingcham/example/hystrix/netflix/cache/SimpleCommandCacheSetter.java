package com.ddingcham.example.hystrix.netflix.cache;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

public class SimpleCommandCacheSetter extends HystrixCommand<Void> {
    private final int id;
    private final String cachePrefix;

    public SimpleCommandCacheSetter(int id, String cachePrefix) {
        super(HystrixCommandGroupKey.Factory.asKey(CacheCommandContexts.CACHE_COMMAND));
        this.id = id;
        this.cachePrefix = cachePrefix;
    }

    @Override
    protected Void run() {
        // persist the value against the datastore
        CacheCommandContexts.CACHE_PREFIX = cachePrefix;
        // flush the cache
        SimpleCommandCacheGetter.flushCache(id);
        // no return -> Void
        return null;
    }
}
