package com.example;

import java.io.Serializable;

public class Survey implements Serializable {
    private Long id;
    private String title;

    public Survey() {
    }

    public Survey(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
