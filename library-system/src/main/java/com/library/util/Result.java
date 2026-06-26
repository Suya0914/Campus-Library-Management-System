package com.library.util;

public class Result {
    private boolean success;
    private String message;
    private Object data;

    public Result() {
    }

    public Result(boolean s, String m) {
        success = s;
        message = m;
    }

    public Result(boolean s, String m, Object d) {
        success = s;
        message = m;
        data = d;
    }

    public static Result ok(String m) {
        return new Result(true, m);
    }

    public static Result ok(String m, Object d) {
        return new Result(true, m, d);
    }

    public static Result error(String m) {
        return new Result(false, m);
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean s) {
        success = s;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String m) {
        message = m;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object d) {
        data = d;
    }
}
