package com.example.planningpoker.group.model;

import java.util.List;

public class Group {
    private String id;
    private List<Question> questions;

    public Group(String  id) {
        this.id = id;
    }

    public Group(String  id, List<Question> questions) {
        this.id = id;
        this.questions = questions;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}
