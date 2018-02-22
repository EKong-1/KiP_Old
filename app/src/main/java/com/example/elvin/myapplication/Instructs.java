package com.example.elvin.myapplication;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Elvin on 2/20/2018.
 */

// Change time stamps to int (seconds)

public class Instructs {
    private int instructID;
    private ArrayList<String> instructList;
    private ArrayList<String> timeStampList;


    public Instructs() {}
    public Instructs(int instructID) {
        this.instructID = instructID;
        this.instructList = new ArrayList<String>();
        this.timeStampList = new ArrayList<String>();

    }

    public void setInstructID(int instructID) {
        this.instructID = instructID;
    }
    public void addInstruct(String i, String tS) {
        this.instructList.add(i);
        this.timeStampList.add(tS);
    }

    public int getInstructID() {
        return this.instructID;
    }
    public ArrayList<String> getInstructs() { return instructList; }
    public ArrayList<String> getTimes() { return timeStampList; }
}
