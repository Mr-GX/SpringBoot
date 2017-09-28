package com.spring.boot.server.demo.advice;

public class ApiAdviceHandler<T> {
    private int status;
    private String msg;
    private T data;

    public ApiAdviceHandler(int status, String msg, T data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    //getter必写 return json
    public int getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }

    @Override
    public String toString() {
        return "ApiAdviceHandler{" +
                "status=" + status +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
