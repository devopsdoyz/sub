package com.api.sub.common.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public  class BusinessErrorCodeExceptionBuilder {

    private final HttpStatus httpStatus; //todo final안하면 BusinessErrorCodeException에서 컴파일에러 왜그럼

    public BusinessErrorCodeException httpStatus(BusinessErrorCode code){
        return BusinessErrorCodeException.httpStatus(httpStatus, code);
    }
    public BusinessErrorCodeException httpStatus(BusinessErrorCode code, Object ... args){
        return BusinessErrorCodeException.httpStatus(httpStatus, code, args);
    }
}
