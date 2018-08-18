package com.millionaire.millionaireserverweb.result;

import lombok.Data;

@Data
public class ResultBean<T> {
    private int code;
    private String message;
    private T data;

    public ResultBean(T data) {
        this.data = data;
    }

    public ResultBean(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
}
