package com.example.surbh_000.pixelmovies;

import android.content.Intent;
import android.os.AsyncTask;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.surbh_000.pixelmovies.R;
import com.google.android.youtube.player.YouTubeBaseActivity;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity {
    String message;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Intent intent=getIntent();
        String request=intent.getExtras().getString("request");
        if(request.equals("newest")){
            message="https://api.themoviedb.org/3/movie/now_playing?api_key=f2abb306a5cb8db33a9ebcce288b615e";
            MyTask myTask=new MyTask();
            myTask.execute(message);
        }
        else if(request.equals("popular")){
            message="https://api.themoviedb.org/3/discover/movie?sort_by=popularity.desc&api_key=f2abb306a5cb8db33a9ebcce288b615e";
            MyTask myTask=new MyTask();
            myTask.execute(message);
        }
        else if (request.equals("upcoming")){
            message="https://api.themoviedb.org/3/movie/upcoming?api_key=f2abb306a5cb8db33a9ebcce288b615e";
            MyTask myTask=new MyTask();
            myTask.execute(message);
        }
        else  {
            message="http://api.themoviedb.org/3/search/movie?query="+request+"&api_key=f2abb306a5cb8db33a9ebcce288b615e";
            StringBuilder builder=new StringBuilder();
            for(int i=0;i<message.length();i++){
                char a = message.charAt(i);
                if (a==' '){
                    builder.append("%20");
                }
                else {
                    builder.append(a);
                }
            }
            message=builder.toString();
            MyTask myTask=new MyTask();
            myTask.execute(message);
        }
    }

    private class MyTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            StringBuilder sb = new StringBuilder();

            try {
                URL url=new URL(strings[0]);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                InputStream io = con.getInputStream();
                String line;
                BufferedReader reader = new BufferedReader(new InputStreamReader(io));
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                }
            } catch (Exception e) {
                Log.e("errorsLog", e.toString());
            }
            return sb.toString();
        }

        @Override
        protected void onPostExecute(String s) {

            showData(s);
        }
    }

    public void showData(String s) {

        try {
            JSONObject obj = new JSONObject(s);
            String[] str;
            ArrayList<String> list = new ArrayList<String>();
            ArrayList<String> list2=new ArrayList<String>();
            ArrayList<String> list3=new ArrayList<String>();


            JSONArray arr = obj.getJSONArray("results");
            for (int i = 0; i < arr.length(); i++) {
                JSONObject obj1 = arr.getJSONObject(i);
                list.add(obj1.getString("title"));
            }
            String[] strtitle = new String[list.size()];
            strtitle = list.toArray(strtitle);
            JSONArray arr1 = obj.getJSONArray("results");
            for (int i = 0; i < arr.length(); i++) {
                JSONObject obj2 = arr1.getJSONObject(i);
                list2.add(obj2.getString("vote_average"));
            }
            String[] strrating = new String[list2.size()];
            strrating = list2.toArray(strrating);
            for(int m=0;m<strrating.length;m++){
                strrating[m]="Rating : "+strrating[m];
            }
            JSONArray arr2 = obj.getJSONArray("results");
            for (int i = 0; i < arr.length(); i++) {
                JSONObject obj3 = arr2.getJSONObject(i);
                list3.add(obj3.getString("id"));
            }
            String[] strid = new String[list3.size()];
            strid = list3.toArray(strid);

            final String strtitle2[]=strtitle;
            final String strid2[]=strid;

            int i=0;
            MyDataAdapter adapter=new MyDataAdapter(getApplicationContext(),R.layout.row_layout);
            for(String title:strtitle){
                MyDataProvider dataProvider=new MyDataProvider(strtitle[i],strrating[i]);
                adapter.add(dataProvider);
                i++;
            }
            ListView listView=(ListView)findViewById(R.id.list);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent intent =new Intent(Main2Activity.this,Main3Activity.class);
                    intent.putExtra("id",strid2[i]);
                    intent.putExtra("overview",strtitle2[i]);
                    startActivity(intent);
                }
            });
        } catch (Exception e) {
            Log.e("errorLog", e.toString());

        }

    }
}
