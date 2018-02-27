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
    private ArrayList<Integer> timeStampList;


    public Instructs() {}
    public Instructs(int instructID) {
        this.instructID = instructID;
        this.instructList = new ArrayList<String>();
        this.timeStampList = new ArrayList<Integer>();

    }
    public Instructs(int instructID, String[] instr, Integer[] times) {
        this.instructID = instructID;
        this.instructList = new ArrayList<String>(Arrays.asList(instr));
        this.timeStampList = new ArrayList<Integer>(Arrays.asList(times));

    }

    public void setInstructID(int instructID) {
        this.instructID = instructID;
    }
    public void addInstruct(String i, int tS) {
        this.instructList.add(i);
        this.timeStampList.add(tS);
    }

    public int getInstructID() {
        return this.instructID;
    }
    public ArrayList<String> getInstructs() { return instructList; }
    public ArrayList<Integer> getTimes() { return timeStampList; }
}
