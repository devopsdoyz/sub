package com.api.sub.common.exception;

public interface BusinessErrorCode {

    String getErrorCode();

    String getMessage();

    default String getMessage(Object ... args){
        return String.format(getMessage(), args);
    }
}
