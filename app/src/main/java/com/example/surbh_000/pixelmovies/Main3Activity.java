package com.example.surbh_000.pixelmovies;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class Main3Activity extends AppCompatActivity {
    Button button = (Button) findViewById(R.id.btntrailer);

    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        Intent intent = getIntent();
        MyTask myTask = new MyTask();
        id = intent.getStringExtra("id");
        String overview = intent.getStringExtra("overview");
        id = "http://api.themoviedb.org/3/movie/" + id + "24668/videos?api_key=f2abb306a5cb8db33a9ebcce288b615e";
        overview = "http://api.themoviedb.org/3/search/movie?query=" + overview + "&api_key=f2abb306a5cb8db33a9ebcce288b615e";
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < overview.length(); i++) {
            char a = overview.charAt(i);
            if (a == ' ') {
                builder.append("%20");
            } else {
                builder.append(a);
            }
        }
        overview = builder.toString();
        myTask.execute(overview);

    }


    public class MyTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            StringBuilder sb = new StringBuilder();
            try {
                URL url = new URL(strings[0]);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                InputStream io = con.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(io));
                String line;
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                }

            } catch (Exception e) {
                Log.e("errorLog", e.toString());
            }
            return sb.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            showData(s);
        }

        public void showData(String s) {
            TextView textView = (TextView) findViewById(R.id.text2);
            try {
                JSONObject obj = new JSONObject(s);
                JSONArray arr = obj.getJSONArray("results");
                JSONObject ob1 = arr.getJSONObject(0);
                textView.setText(ob1.getString("overview"));

            } catch (Exception e) {
                Log.e("errorLog", e.toString());
            }

        }
    }
    public class MyTask1 extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            StringBuilder sb = new StringBuilder();
            try {
                URL url = new URL(strings[0]);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                InputStream io = con.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(io));
                String line;
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                }

            } catch (Exception e) {
                Log.e("errorLog", e.toString());
            }
            return sb.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            try {
                JSONObject obj = new JSONObject(s);
                JSONArray arr = obj.getJSONArray("results");
                JSONObject ob1 = arr.getJSONObject(0);
                String m=ob1.getString("key");
                Intent intent=new Intent(getApplicationContext(),Trailer.class);
                intent.putExtra("key",m);
                startActivity(intent);

            } catch (Exception e) {
                Log.e("errorLog", e.toString());
            }
        }


    }
}
