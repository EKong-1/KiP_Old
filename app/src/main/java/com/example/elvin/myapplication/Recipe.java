package com.example.elvin.myapplication;

/**
 * Created by Elvin on 2/6/2018.
 */

public class Recipe {
    private int id;
    private String name;
    private String instructID;

    public Recipe() {}
    public Recipe(int id, String name, String instructID) {
        this.id = id;
        this.name = name;
        this.instructID = instructID;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setInstructID(String instructID) {
        this.instructID = instructID;
    }

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getInstructID() {
        return instructID;
    }
}
