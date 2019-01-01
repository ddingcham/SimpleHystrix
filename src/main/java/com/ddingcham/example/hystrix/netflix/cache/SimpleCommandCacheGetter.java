package com.ddingcham.example.hystrix.netflix.cache;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixRequestCache;
import com.netflix.hystrix.strategy.concurrency.HystrixConcurrencyStrategyDefault;

public class SimpleCommandCacheGetter extends HystrixCommand<String> {
    static final HystrixCommandKey COMMAND_ID = HystrixCommandKey.Factory.asKey("SimpleCommandCacheGetter");
    private final int id;

    public SimpleCommandCacheGetter(int id) {
        super(HystrixCommand.Setter.withGroupKey(CacheCommandContexts.CACHE_COMMAND_KEY)
                .andCommandKey(COMMAND_ID));
        this.id = id;
    }

    @Override
    protected String getCacheKey() {
        return String.valueOf(id);
    }

    @Override
    protected String run() {
        // represents a remote data store
        return CacheCommandContexts.CACHE_PREFIX + id;
    }

    // Allow the cache to the flushed for this object.
    public static void flushCache(int id) {
        HystrixRequestCache.getInstance(COMMAND_ID,
                HystrixConcurrencyStrategyDefault.getInstance()).clear(String.valueOf(id));
    }
}
