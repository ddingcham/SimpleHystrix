package com.ddingcham.example.hystrix.netflix;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class SimpleCommandFailureTest {

    @Test // (expected = RuntimeException.class) -> fail // call getFallback()
    public void execute() throws Exception {
        SimpleCommandFailure command = new SimpleCommandFailure("execute");
        // [hystrix-SimpleCommand-1] DEBUG com.netflix.hystrix.AbstractCommand
        // - Error executing HystrixCommand.run(). Proceeding to fallback logic ...
        assertThat(command.execute())
                .contains("execute")
                //[hystrix-SimpleCommand-2] DEBUG com.netflix.hystrix.AbstractCommand
                // - Error executing HystrixCommand.run(). Proceeding to fallback logic ..
                .isEqualTo(new SimpleCommandFailure("execute").queue().get());
    }
}