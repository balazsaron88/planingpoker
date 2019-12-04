package com.example.planningpoker.group.model;

import java.io.Serializable;

public class Question implements Serializable {

    private String id;
    private String question;

    public Question(String id, String question) {
        this.id = id;
        this.question = question;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }
}
