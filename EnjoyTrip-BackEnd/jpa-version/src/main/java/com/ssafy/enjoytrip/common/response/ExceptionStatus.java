package com.ssafy.enjoytrip.common.response;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
public enum ExceptionStatus implements ResponseStatus {

    // 1000: 요청 성공
    SUCCESS(1000,HttpStatus.OK.value(), "요청에 성공하였습니다."),

    // 2000: BAD_REQUEST
    BAD_REQUEST(2000, HttpStatus.BAD_REQUEST.value(), "유효하지 않은 요청입니다."),
    URL_NOT_FOUND(2001, HttpStatus.BAD_REQUEST.value(), "유효하지 않은 URL 입니다."),
    METHOD_NOT_SUPPORTED(2002, HttpStatus.BAD_REQUEST.value(), "해당 URL에서는 지원하지 않는 HTTP Method 입니다."),

    // 3000: INTERNAL_SERVER_ERROR
    SERVER_ERROR(3000, HttpStatus.INTERNAL_SERVER_ERROR.value(), "서버에서 오류가 발생하였습니다."),
    DATABASE_ERROR(3001, HttpStatus.INTERNAL_SERVER_ERROR.value(), "데이터베이스에서 오류가 발생하였습니다."),
    BAD_SQL_GRAMMAR(3002, HttpStatus.INTERNAL_SERVER_ERROR.value(), "SQL에 오류가 있습니다."),

    // 4000: UNAUTHORIZED
    JWT_ERROR(4000, HttpStatus.UNAUTHORIZED.value(), "JWT에서 오류가 발생하였습니다."),
    TOKEN_NOT_FOUND(4001, HttpStatus.BAD_REQUEST.value(), "토큰이 HTTP Header에 없습니다."),
    UNSUPPORTED_TOKEN_TYPE(4002, HttpStatus.BAD_REQUEST.value(), "지원되지 않는 토큰 형식입니다."),
    INVALID_TOKEN(4003, HttpStatus.UNAUTHORIZED.value(), "유효하지 않은 토큰입니다."),
    MALFORMED_TOKEN(4004, HttpStatus.UNAUTHORIZED.value(), "토큰이 올바르게 구성되지 않았습니다."),
    EXPIRED_TOKEN(4005, HttpStatus.UNAUTHORIZED.value(), "만료된 토큰입니다."),
    TOKEN_MISMATCH(4006, HttpStatus.UNAUTHORIZED.value(), "로그인 정보가 토큰 정보와 일치하지 않습니다."),

    // 5000: MEMBER
    INVALID_MEMBER_VALUE(5000, HttpStatus.BAD_REQUEST.value(), "회원가입 요청에서 잘못된 값이 존재합니다."),
    DUPLICATE_EMAIL(5001, HttpStatus.BAD_REQUEST.value(), "이미 존재하는 이메일입니다."),
    MEMBER_NOT_FOUND(5002, HttpStatus.BAD_REQUEST.value(), "존재하지 않는 회원입니다."),
    PASSWORD_NO_MATCH(5003, HttpStatus.BAD_REQUEST.value(), "비밀번호가 일치하지 않습니다."),
    DUPLICATE_ID(5004, HttpStatus.BAD_REQUEST.value(), "이미 존재하는 아이디입니다."),

    // 6000: ATTRACTION
    INVALID_ATTRACTION_VALUE(6000, HttpStatus.BAD_REQUEST.value(), "관광지 정보 요청에서 잘못된 값이 존재합니다."),
    SIDO_NOT_FOUND(6001, HttpStatus.BAD_REQUEST.value(), "존재하지 않는 시도코드입니다."),
    GUGUN_NOT_FOUND(6002, HttpStatus.BAD_REQUEST.value(), "존재하지 않는 구군코드입니다."),
    CONTENT_TYPE_NOT_FOUND(6003, HttpStatus.BAD_REQUEST.value(), "존재하지 않는 관광지 유형입니다."),
    ATTRACTION_NOT_FOUND(6004, HttpStatus.BAD_REQUEST.value(), "존재하지 않는 관광지입니다.");

    // 7000: BOARD



    private final int code;
    private final int status;
    private final String message;

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public int getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
