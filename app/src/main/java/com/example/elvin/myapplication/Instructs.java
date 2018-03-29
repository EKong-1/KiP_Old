package com.example.elvin.myapplication;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Elvin on 2/20/2018.
 */

// Change time stamps to int (seconds)

public class Instructs {
    private int ID;
    private int instructID;
    private ArrayList<String> instructList;
    private ArrayList<Integer> timeStampList;


    public Instructs() {}
    public Instructs(int instructID) {
        this.ID = -1;
        this.instructID = instructID;
        this.instructList = new ArrayList<String>();
        this.timeStampList = new ArrayList<Integer>();
    }
    public Instructs(int ID, int instructID, ArrayList<String> instr, ArrayList<Integer> times) {
        this.ID = ID;
        this.instructID = instructID;
        this.instructList = instr;
        this.timeStampList = times;

    }

    public void setInstructID(int instructID) {
        this.instructID = instructID;
    }
    public void addInstruct(String i, int tS) {
        this.instructList.add(i);
        this.timeStampList.add(tS);
    }
    public void insertInstruct(String i, int tS, int pos) {
        this.instructList.add(pos, i);
        this.timeStampList.add(pos, tS);
    }

    public void removeInstruct(int pos) {
        this.instructList.remove(pos);
        this.timeStampList.remove(pos);
    }

    public int getID(){return this.ID;}
    public int getInstructID() {
        return this.instructID;
    }
    public ArrayList<String> getInstructs() { return instructList; }
    public ArrayList<Integer> getTimes() { return timeStampList; }
}
