package com.example.mathgameapp;

public class Score {
    String id;
    String operation;
    String degree;

    public Score(){

    }
    public Score(String id, String operation, String degree) {
        this.id = id;
        this.operation = operation;
        this.degree = degree;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }
}
