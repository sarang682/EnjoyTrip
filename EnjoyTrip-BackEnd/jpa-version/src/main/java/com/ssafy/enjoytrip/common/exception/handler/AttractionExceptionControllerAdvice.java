package com.ssafy.enjoytrip.common.exception.handler;

import com.ssafy.enjoytrip.common.exception.AttractionException;
import com.ssafy.enjoytrip.common.response.ErrorResponse;
import jakarta.annotation.Priority;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Priority(0)
@RestControllerAdvice
public class AttractionExceptionControllerAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(AttractionException.class)
    public ErrorResponse handleAttractionException(AttractionException e) {
        return new ErrorResponse(e.getExceptionStatus(), e.getMessage());
    }

}