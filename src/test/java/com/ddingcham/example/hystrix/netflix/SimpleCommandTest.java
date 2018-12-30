package com.ddingcham.example.hystrix.netflix;

import org.junit.Test;

import java.util.concurrent.Future;

import static org.assertj.core.api.Assertions.assertThat;

public class SimpleCommandTest {

    @Test
    public void execute() throws Exception {
        SimpleCommand command = new SimpleCommand("execute");
        assertThat(command.execute()).contains("execute");
        assertThat(command.execute()).isEqualTo(command.queue().get());
    }

    @Test
    public void queue() throws Exception {
        assertThat(new SimpleCommand("async").queue());
        Future<String> future = new SimpleCommand("async").queue();
        assertThat(future.get());
    }
}
