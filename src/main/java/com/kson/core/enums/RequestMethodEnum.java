package com.kson.core.enums;

public enum RequestMethodEnum {

    POST("POST"),
    GET("GET");

    private String method;

    RequestMethodEnum(String method) {
        this.method = method;
    }
}
