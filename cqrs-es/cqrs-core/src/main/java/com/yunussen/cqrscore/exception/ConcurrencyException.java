package com.yunussen.cqrscore.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ConcurrencyException extends RuntimeException {

    public ConcurrencyException(String errorMessage) {
        super(errorMessage);
    }
}
