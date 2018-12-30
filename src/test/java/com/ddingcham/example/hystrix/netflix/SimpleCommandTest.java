package com.ddingcham.example.hystrix.netflix;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import rx.Observable;
import rx.Observer;
import rx.functions.Action1;

import java.util.concurrent.Future;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class SimpleCommandTest {

    @Test
    public void execute() throws Exception {
        SimpleCommand command = new SimpleCommand("execute");
        assertThat(command.execute())
                .contains("execute")
                .isEqualTo(new SimpleCommand("execute").queue().get());
    }

    @Test
    public void queue() throws Exception {
        Future<String> future = new SimpleCommand("queue").queue();
        assertThat(future.get())
                .isEqualTo(new SimpleCommand("queue")
                        .toObservable()
                        .toBlocking()
                        .toFuture()
                        .get());
    }

    // blocking
    @Test
    public void observe_ToBlocking() {
        Observable<String> hotObservable = new SimpleCommand("hot").observe();
        assertThat(hotObservable.toBlocking().single())
                .contains("hot");
    }

    // non-blocking
    // ignore errors and onCompleted signal
    @Test
    public void observe_NonBlocking_Action1() {
        Observable<String> hotObservable = new SimpleCommand("hot").observe();

        // anonymous inner-class
        hotObservable.subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                log.debug("Action1.call() : {}", s);
            }
        });

        // lambda closures
        hotObservable.subscribe((s) -> log.debug("call() : {}", s));
    }

    // non-blocking
    // doesn't do assertions (including error handling)
    @Test
    public void observe_NonBlocking_Observer() {
        Observable<String> hotObservable = new SimpleCommand("hot").observe();

        // anonymous inner-class
        hotObservable.subscribe(new Observer<String>() {
            @Override
            public void onCompleted() {
                // nothing needed here
            }

            @Override
            public void onError(Throwable e) {
                // error handling
            }

            @Override
            public void onNext(String s) {
                log.debug("Observer.onNext() : {}", s);
            }
        });

        // lambda closures
        hotObservable.subscribe(
                (v) -> {
                    log.debug("onNext() : {}", v);
                },
                (exception) -> {
                    log.debug("onError(Throwable) : {}", exception);

                });
    }
}
