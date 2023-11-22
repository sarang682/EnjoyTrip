package com.ssafy.enjoytrip.common.exception.handler;

import com.ssafy.enjoytrip.common.exception.JwtBadRequestException;
import com.ssafy.enjoytrip.common.exception.JwtUnauthorizedException;
import com.ssafy.enjoytrip.common.response.ErrorResponse;
import jakarta.annotation.Priority;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Priority(0)
@RestControllerAdvice
public class JwtExceptionControllerAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(JwtBadRequestException.class)
    public ErrorResponse handleJwtBadRequestException(JwtBadRequestException e) {
        return new ErrorResponse(e.getExceptionStatus());
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(JwtUnauthorizedException.class)
    public ErrorResponse handle_JwtUnauthorizedException(JwtUnauthorizedException e) {
        return new ErrorResponse(e.getExceptionStatus());
    }

}