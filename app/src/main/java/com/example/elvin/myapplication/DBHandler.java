package com.example.elvin.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

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

    // Adding new Instructions for one Recipe
    public void addInstruct(Instructs instruct) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        int id = instruct.getInstructID();
        ArrayList<String> ins = instruct.getInstructs();
        ArrayList<String> times = instruct.getTimes();

        for (int i = 0; i < ins.size(); i++) {
            values.put(KEY_INSTRUCT_ID, id));
            values.put(KEY_INSTRUCTION, ins.get(i));
            values.put(KEY_TIME_STAMP, times.get(i));
            db.insert(TABLE_INSTRUCTS, null, values);
            values.clear();
        }
    }

    // Adding new Recipe
    public void addRecipe(Recipe recipe) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, recipe.getName()); // Recipe Name
        values.put(KEY_INSTRUCT_ID, recipe.getInstructID()); // ID that points to Recipe Instructions
        // Inserting Row
        db.insert(TABLE_RECIPES, null, values);
        db.close(); // Closing database connection
    }

    // Getting Instructions for one Recipe by instuct_id
    public Instructs getInstructs(int instruct_id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_INSTRUCTS, new String[] {KEY_INSTRUCT_ID, KEY_INSTRUCTION, KEY_TIME_STAMP},
                KEY_INSTRUCT_ID + "=?", new String[] {String.valueOf(instruct_id)},
                null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        Instructs inst = new Instructs(instruct_id);
        while (cursor != null) {
            inst.addInstruct(cursor.getString(1), cursor.getString(2));
            cursor.moveToNext();
        }
        return inst;
    }

    // Getting one Recipe by id
    public Recipe getRecipe(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_RECIPES, new String[] {KEY_ID, KEY_NAME, KEY_INSTRUCT_ID}, KEY_ID + "=?",
                new String[] {String.valueOf(id)}, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        Recipe contact = new Recipe(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2));
        // return recipe
        return contact;
    }

    // Getting All Recipes
    public List<Recipe> getAllRecipes() {
        List<Recipe> recipeList = new ArrayList<Recipe>();
        // Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_RECIPES;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Recipe recipe = new Recipe();
                recipe.setId(Integer.parseInt(cursor.getString(0)));
                recipe.setName(cursor.getString(1));
                recipe.setInstructID(cursor.getString(2));
                // Adding contact to list
                recipeList.add(recipe);
            } while (cursor.moveToNext());
        }
        // return contact list
        return recipeList;
    }

    // Getting recipes Count
    public int getRecipesCount() {
        String countQuery = "SELECT * FROM " + TABLE_RECIPES;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
        // return count
        return cursor.getCount();
    }
//
//    // Updating instructions
//    public int updateInstructs(Instructs instruct) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues values = new ContentValues();
//
//        return 0;
//    }

    // Updating a recipe
    public int updateRecipe(Recipe recipe) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, recipe.getName());
        values.put(KEY_INSTRUCT_ID, recipe.getInstructID());
        // updating row
        return db.update(TABLE_RECIPES, values, KEY_ID + " = ?",
                new String[]{String.valueOf(recipe.getId())});
    }

    // Deleting a recipe
    public void deleteRecipe(Recipe recipe) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_RECIPES, KEY_ID + " = ?",
                new String[] { String.valueOf(recipe.getId()) });
        db.close();
    }
}
