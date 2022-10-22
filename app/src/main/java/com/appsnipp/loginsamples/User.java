package com.appsnipp.loginsamples;

public class User {

    private String userName;
    private int rollNo;
    private String branch;

    public User(String userName, int rollNo, String branch) {
        this.userName = userName;
        this.rollNo = rollNo;
        this.branch = branch;
    }

    public String getUserName() {
        return userName;
    }

    public int getRollNo() {
        return rollNo;
    }

    public String getBranch() {
        return branch;
    }
}
