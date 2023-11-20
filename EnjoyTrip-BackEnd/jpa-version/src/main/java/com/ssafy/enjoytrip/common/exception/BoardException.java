package com.ssafy.enjoytrip.common.exception;

import com.ssafy.enjoytrip.common.response.ResponseStatus;
import lombok.Getter;

@Getter
public class BoardException extends RuntimeException {

    private final ResponseStatus exceptionStatus;

    public BoardException(ResponseStatus exceptionStatus) {
        super(exceptionStatus.getMessage());
        this.exceptionStatus = exceptionStatus;
    }

    public BoardException(ResponseStatus exceptionStatus, String message) {
        super(message);
        this.exceptionStatus = exceptionStatus;
    }

}