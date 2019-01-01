package com.ddingcham.example.hystrix.netflix.cache;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class CommandCacheInvalidationTest {
    HystrixRequestContext context;

    @Before
    public void initContext() {
        context = HystrixRequestContext.initializeContext();
    }

    @After
    public void shutdownContext() {
        context.shutdown();
    }

    @Test
    public void fetchCache() {
        final int id = 1;

        assertThat(CacheCommandContexts.CACHE_PREFIX + id)
                .isEqualTo(new SimpleCommandCacheGetter(id).execute());

        SimpleCommandCacheGetter getterCommand = new SimpleCommandCacheGetter(id);
        getterCommand.execute();

        assertThat(getterCommand.isResponseFromCache()).isTrue();

        // id에 따라 캐시 적용
        // SimpleCommandCacheSetter(10, ~) -> id 1에 대한 HystrixCommand(SimpleCommandCacheGetter)는 캐싱 유지
        new SimpleCommandCacheSetter(
                id, CacheCommandContexts.CACHE_PREFIX + id + "_")
                .execute();

        SimpleCommandCacheGetter commandAfterSet = new SimpleCommandCacheGetter(id);

        assertThat(commandAfterSet.isResponseFromCache()).isFalse();
        assertThat(commandAfterSet.execute()).contains(id + "_" + id);
//        log.debug("commandAfterSet.execute() : {}", commandAfterSet.execute());
//        com.ddingcham.example.hystrix.netflix.cache.CommandCacheInvalidationTest - commandAffterSet.execute() : ValueCached_1_1
    }

}
