package com.raulgarcan.wikitarkov.pojo;

public class ErrorMsg {
    private String message;

    public ErrorMsg(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
