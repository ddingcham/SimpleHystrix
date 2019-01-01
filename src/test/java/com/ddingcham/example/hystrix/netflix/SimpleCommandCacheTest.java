package com.ddingcham.example.hystrix.netflix;

import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SimpleCommandCacheTest {
    HystrixRequestContext context;

    @Before
    public void setUp() {
        context = HystrixRequestContext.initializeContext();
    }

    @After
    public void setOut() {
        context.shutdown();
    }

    @Test
    public void withoutCacheHits() {
        SimpleCommandCache command2a = new SimpleCommandCache(2);
        SimpleCommandCache command1a = new SimpleCommandCache(1);
        SimpleCommandCache command0a = new SimpleCommandCache(0);
        SimpleCommandCache command58670a = new SimpleCommandCache(58670);

        assertThat(command2a.execute()).isTrue();
        assertThat(command2a.isResponseFromCache()).isFalse();

        assertThat(command1a.execute()).isFalse();
        assertThat(command1a.isResponseFromCache()).isFalse();

        assertThat(command0a.execute()).isTrue();
        assertThat(command0a.isResponseFromCache()).isFalse();

        assertThat(command58670a.execute()).isTrue();
        assertThat(command58670a.isResponseFromCache()).isFalse();
    }

    @Test
    public void withCacheHits() {
        SimpleCommandCache command2a = new SimpleCommandCache(2);
        SimpleCommandCache command2b = new SimpleCommandCache(2);

        assertThat(command2a.execute()).isTrue();
        assertThat(command2a.isResponseFromCache()).isFalse();

        assertThat(command2b.execute()).isTrue();
        assertThat(command2b.isResponseFromCache()).isTrue();

        setOut();
        setUp();

        SimpleCommandCache command2c = new SimpleCommandCache(2);

        assertThat(command2c.execute()).isTrue();
        assertThat(command2c.isResponseFromCache()).isFalse();
    }
}
