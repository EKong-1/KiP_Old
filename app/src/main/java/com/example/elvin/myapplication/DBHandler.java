package com.example.elvin.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Elvin on 2/1/2018.
 */

public class DBHandler extends SQLiteOpenHelper {
    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "recipeInfo";
    // Table Names
    private static final String TABLE_RECIPES = "recipes";
    private static final String TABLE_INSTRUCTS = "instructs";
    // Recipe Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_INSTRUCT_ID = "instruct_id";
    // Instruction Table Column names
    private static final String KEY_INSTRUCTION = "instruction";
    private static final String KEY_TIME_STAMP = "time_stamp";

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_RECIPE_TABLE = "CREATE TABLE " + TABLE_RECIPES + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_INSTRUCT_ID + " INTEGER" + ")";

        String CREATE_INSTRUCTS_TABLE = "CREATE TABLE " + TABLE_INSTRUCTS + "("
                + KEY_INSTRUCT_ID + " INTEGER PRIMARY KEY," + KEY_INSTRUCTION + " TEXT,"
                + KEY_TIME_STAMP + " TEXT,FOREGIN KEY(" + KEY_INSTRUCT_ID +") REFERENCES "
                + TABLE_RECIPES + "(" + KEY_INSTRUCT_ID + "))";

        db.execSQL(CREATE_RECIPE_TABLE);
        db.execSQL(CREATE_INSTRUCTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RECIPES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INSTRUCTS);
        // Creating tables again
        onCreate(db);
    }
}
