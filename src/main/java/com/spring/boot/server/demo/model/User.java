package com.spring.boot.server.demo.model;

public class User {
    private long id;
    private String mobile;
    private int age;

    public User(long id, String mobile, int age) {
        this.id = id;
        this.mobile = mobile;
        this.age = age;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
