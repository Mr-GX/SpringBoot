package com.spring.boot.server.demo.advice;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiAdviceHandler<T> {
    private int status;
    private String msg;
    private T data;
}
