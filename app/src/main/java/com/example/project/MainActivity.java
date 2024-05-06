package com.example.project;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private final String JSON_URL = "HTTPS_URL_TO_JSON_DATA_CHANGE_THIS_URL";
    private final String JSON_FILE = "mountains.json";
    ArrayList<Mountain> items = new ArrayList<>();

    ArrayList<RecyclerViewItem> recyclerViewItems = new ArrayList<>();


    private RecyclerViewAdapter adapter;

    private Gson gson;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        items.add(new Mountain("Berf"));
        items.add(new Mountain("Neft"));
        items.add(new Mountain("Gurt"));

        new JsonFile((Activity) this, (JsonTask.JsonTaskListener) this).execute(JSON_FILE);
        //new JsonTask(this).execute(JSON_URL);


        for (int i =0;i<items.size();i++) {
            Log.d("Vinge", "" + items.get(i).toString());
            recyclerViewItems.add(new RecyclerViewItem(items.get(i).toString()));
        }



        adapter = new RecyclerViewAdapter(this, recyclerViewItems, new RecyclerViewAdapter.OnClickListener() {
            @Override
            public void onClick(RecyclerViewItem item) {
                Toast.makeText(MainActivity.this, item.getTitle(), Toast.LENGTH_SHORT).show();
            }
        });
        RecyclerView view = findViewById(R.id.recycler_view);
        view.setLayoutManager(new LinearLayoutManager(this));
        view.setAdapter(adapter);


    }

}
