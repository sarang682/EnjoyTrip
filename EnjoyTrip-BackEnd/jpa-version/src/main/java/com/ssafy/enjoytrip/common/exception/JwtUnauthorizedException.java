package com.ssafy.enjoytrip.common.exception;

import com.ssafy.enjoytrip.common.response.ResponseStatus;
import lombok.Getter;

@Getter
public class JwtUnauthorizedException extends RuntimeException {

    private final ResponseStatus exceptionStatus;

    public JwtUnauthorizedException(ResponseStatus exceptionStatus) {
        super(exceptionStatus.getMessage());
        this.exceptionStatus = exceptionStatus;
    }

}