package com.example.elvin.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.elvin.myapplication.MESSAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DBHandler db = new DBHandler(this);
        db.onUpgrade(db.getWritableDatabase(), 0, 1);
        Log.d("Insert: ", "Inserting ..");
        db.addRecipe(new Recipe(1, "PB&J", 1));
        db.addInstruct(new Instructs(1,1, new ArrayList<String>(Arrays.asList("Spread peanut butter", "Spread jelly",
                "Place slices together.")), new ArrayList<Integer>(Arrays.asList(0, 10, 20))));
        db.addRecipe(new Recipe(2, "Cereal", 2));
        db.addInstruct(new Instructs(2,2, new ArrayList<String>(Arrays.asList("Pour Milk", "Pour cereal")),
                new ArrayList<Integer>(Arrays.asList(0, 8))));
        db.addRecipe(new Recipe(3, "Toast", 3));
        db.addInstruct(new Instructs(3,3, new ArrayList<String>(Arrays.asList("Place bread in toaster", "Take toast out")),
                new ArrayList<Integer>(Arrays.asList(0, 30))));
        db.addRecipe(new Recipe(4, "Scrambled Eggs", 4));
        db.addInstruct(new Instructs(4,1, new ArrayList<String>(Arrays.asList("Crack eggs and beat in bowl", "Pour oil on pan and let it heat",
                "Pour in eggs, stirring frequently", "Take out eggs and place on plate")), new ArrayList<Integer>(Arrays.asList(0, 60, 80, 110))));

        Log.d("Reading: ", "Reading all recipes ..");
        List<Recipe> recipes = db.getAllRecipes();

        for (Recipe recipe: recipes) {
            String log = "Id: " + recipe.getId() + ", Name: " + recipe.getName() + ", InstructID: " + recipe.getInstructID();
            Log.d("Shop: ", log);
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void sendMessage(View view) {
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = (EditText) findViewById(R.id.editText);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }
}
