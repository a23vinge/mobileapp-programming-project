package com.example.project;

import android.os.Bundle;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


import android.view.Menu;
import android.view.MenuItem;
import androidx.appcompat.widget.Toolbar;


@SuppressWarnings("FieldCanBeLocal")
public class MainActivity extends AppCompatActivity implements JsonTask.JsonTaskListener  {



    private final String JSON_URL = "HTTPS_URL_TO_JSON_DATA_CHANGE_THIS_URL";
    private final String JSON_FILE = "Berry.json";
    ArrayList<Berry> items = new ArrayList<>();
    private WebView myWebView;

    ArrayList<RecyclerViewItem> recyclerViewItems = new ArrayList<>();


    private RecyclerViewAdapter adapter;

    private Gson gson;
    public void showExternalWebPage(){
        // TODO: Add your code for showing external web page here
        myWebView.loadUrl("file:///android_res/layout/activity_main.xml");
    }

    public void showInternalWebPage(){
        // TODO: Add your code for showing internal web page here
        myWebView.loadUrl("https://google.se");
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        myWebView =  findViewById(R.id.my_webview) ;
        myWebView.setWebViewClient(new WebViewClient());    // Do not open in Chrome!
        WebSettings websettings= myWebView.getSettings();
        websettings.setJavaScriptEnabled(true);
        myWebView.loadUrl("file:///android_res/layout/activity_main.xml");

        new JsonFile(this, this).execute(JSON_FILE);


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
        if (id == R.id.action_external_web) {
            Log.d("==>","Will display external web page");
            showExternalWebPage();
            return true;
        }

        if (id == R.id.action_internal_web) {
            Log.d("==>","Will display internal web page");
            showInternalWebPage();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onPostExecute(String json) {
        gson = new Gson();

        Log.d("MainActivity","" + json);

        Type type = new TypeToken<List<Berry>>() {}.getType();
        items = gson.fromJson(json, type);
        for (Berry b : items) {

        }
    }

}