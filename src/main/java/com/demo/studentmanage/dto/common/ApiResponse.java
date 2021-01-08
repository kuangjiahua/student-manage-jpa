package com.demo.studentmanage.dto.common;

import lombok.Data;

/**
 * 接口统一返回对象
 * @author kuangjiahua
 * @date   2021/01/04
 */
@Data
public class ApiResponse {

    public static String DEFAULT_MSG = "成功";
    public static int DEFAULT_CODE = 0;
    private int code;
    private String msg;
    private Object result;

    public ApiResponse() {
        this(DEFAULT_CODE, DEFAULT_MSG, null);
    }

    public ApiResponse(Object result) {
        this();
        this.result = result;
    }

    public ApiResponse(int code, String msg) {
        this(code, msg, null);
    }

    public ApiResponse(int code, String message, Object result) {
        this.code = code;
        if (!message.isEmpty()) {
            this.msg = message;
        }
        this.result = result;
    }


    public ApiResponse setData(Object result) {
        this.result = result;
        return this;
    }

}

