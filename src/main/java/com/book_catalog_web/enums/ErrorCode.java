package com.book_catalog_web.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ErrorCode {
    INVALID_DATA(1),
    INTERNAL_ERROR(2),
    NETWORK_ERROR(3),
    DATA_NOT_FOUND(4),
    OTHER_ERROR(5);

    private int code;

    private ErrorCode(int code){
        this.code = code;
    }

    @JsonValue
    public int code(){
        return code;
    }

}
