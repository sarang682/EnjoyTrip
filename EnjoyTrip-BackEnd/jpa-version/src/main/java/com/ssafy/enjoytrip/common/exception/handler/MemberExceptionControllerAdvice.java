package com.ssafy.enjoytrip.common.exception.handler;

import com.ssafy.enjoytrip.common.exception.MemberException;
import com.ssafy.enjoytrip.common.response.ErrorResponse;
import jakarta.annotation.Priority;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Priority(0)
@RestControllerAdvice
public class MemberExceptionControllerAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MemberException.class)
    public ErrorResponse handleMemberException(MemberException e) {
        return new ErrorResponse(e.getExceptionStatus(), e.getMessage());
    }

}