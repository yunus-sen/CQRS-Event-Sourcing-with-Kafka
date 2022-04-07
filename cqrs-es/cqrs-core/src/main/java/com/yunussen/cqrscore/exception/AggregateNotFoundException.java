package com.yunussen.cqrscore.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class AggregateNotFoundException extends RuntimeException {
    public AggregateNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
