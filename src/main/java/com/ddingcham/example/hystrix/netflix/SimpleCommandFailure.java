package com.ddingcham.example.hystrix.netflix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.exception.HystrixBadRequestException;
import com.netflix.hystrix.exception.HystrixRuntimeException.FailureType;

public class SimpleCommandFailure extends HystrixCommand<String> {
    static final String COMMAND_ID = "SimpleCommand";
    static final String MESSAGE = "this command always fails";
    private final String name;
    private final FailureType expectedCause;

    public SimpleCommandFailure(String name) {
        this(name, FailureType.SHORTCIRCUIT);
    }

    public SimpleCommandFailure(String name, FailureType expectedCause) {
        super(HystrixCommandGroupKey.Factory.asKey(COMMAND_ID));
        this.name = name;
        this.expectedCause = expectedCause;
    }

    @Override
    protected String getFallback() {
        return COMMAND_ID + " : " + name;
    }

    @Override
    protected String run() {
        if (expectedCause.equals(FailureType.BAD_REQUEST_EXCEPTION)) {
            throw new HystrixBadRequestException(MESSAGE);
        }
        throw new RuntimeException(MESSAGE);
    }
}
