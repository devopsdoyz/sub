package com.api.sub.common.exception;

import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import org.springframework.util.ObjectUtils;

import java.util.function.Consumer;

public final class BusinessErrorCodeException extends ResponseStatusException {

    private final BusinessErrorCode businessErrorCode;

    BusinessErrorCodeException(HttpStatus status, BusinessErrorCode businessErrorCode) {
        super(status, businessErrorCode.getMessage());
        this.businessErrorCode = businessErrorCode;
    }

    BusinessErrorCodeException(HttpStatus status, BusinessErrorCode businessErrorCode, Object ... args) {
        super(status, businessErrorCode.getMessage(args));
        this.businessErrorCode = businessErrorCode;
    }

    public boolean hasReason(){
        return !ObjectUtils.isEmpty(getReason());
    }

    public String getErrorCode(){
        return businessErrorCode.getErrorCode();
    }
    public boolean hasErrorCode() {
        return !ObjectUtils.isEmpty(getErrorCode());
    }

    public static BusinessErrorCodeException httpStatus(HttpStatus httpStatus, BusinessErrorCode code){
        return new BusinessErrorCodeException(httpStatus, code);
    }

    public static BusinessErrorCodeException httpStatus(HttpStatus httpStatus, BusinessErrorCode code, Object ... args){
        return new BusinessErrorCodeException(httpStatus, code, args);
    }

    public static final BusinessErrorCodeExceptionBuilder OK = new BusinessErrorCodeExceptionBuilder(HttpStatus.OK);
    public static final BusinessErrorCodeExceptionBuilder MOVED_PERMANENTLY = new BusinessErrorCodeExceptionBuilder(HttpStatus.MOVED_PERMANENTLY);
    public static final BusinessErrorCodeExceptionBuilder FOUND = new BusinessErrorCodeExceptionBuilder(HttpStatus.FOUND);
    public static final BusinessErrorCodeExceptionBuilder BAD_REQUEST = new BusinessErrorCodeExceptionBuilder(HttpStatus.BAD_REQUEST);
    public static final BusinessErrorCodeExceptionBuilder UNAUTHORIZED = new BusinessErrorCodeExceptionBuilder(HttpStatus.UNAUTHORIZED);
    public static final BusinessErrorCodeExceptionBuilder NOT_FOUND = new BusinessErrorCodeExceptionBuilder(HttpStatus.NOT_FOUND);
    public static final BusinessErrorCodeExceptionBuilder INTERNAL_SERVER_ERROR = new BusinessErrorCodeExceptionBuilder(HttpStatus.INTERNAL_SERVER_ERROR);

    void ifReason(Consumer<String> t, Consumer<String> f) {
        if(!hasReason()){
            f.accept("");
            return;
        }
        t.accept(getReason());
    }
}
