package com.ssafy.enjoytrip.common.exception;

import com.ssafy.enjoytrip.common.response.ResponseStatus;
import lombok.Getter;

@Getter
public class PlanException extends RuntimeException {

    private final ResponseStatus exceptionStatus;

    public PlanException(ResponseStatus exceptionStatus) {
        super(exceptionStatus.getMessage());
        this.exceptionStatus = exceptionStatus;
    }

    public PlanException(ResponseStatus exceptionStatus, String message) {
        super(message);
        this.exceptionStatus = exceptionStatus;
    }

}