package com.ddingcham.example.hystrix.netflix;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SimpleCommandTest {

    @Test
    public void testSynchronous() {
        assertThat(new SimpleCommand("sync").execute()).contains("sync");
    }


}
