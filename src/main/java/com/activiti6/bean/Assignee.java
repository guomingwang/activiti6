package com.activiti6.bean;

import java.io.Serializable;

public class Assignee implements Serializable {

    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
