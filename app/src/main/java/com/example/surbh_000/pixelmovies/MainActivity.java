package com.example.surbh_000.pixelmovies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.view.View;
import android.widget.Button;

public class  MainActivity extends AppCompatActivity implements View.OnClickListener{
    String request;
    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent=getIntent();
        Button btnpopular=(Button)findViewById(R.id.btnpopular);
        Button btnnewest=(Button)findViewById(R.id.btnnewest);
        Button btnupcoming=(Button)findViewById(R.id.btnupcoming);
        Button btnsearch=(Button)findViewById(R.id.btnsearch);
        btnsearch.setOnClickListener(this);
        btnnewest.setOnClickListener(this);
        btnpopular.setOnClickListener(this);
        btnupcoming.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.btnnewest){
            Intent intent1=new Intent(this,Main2Activity.class);
            request="newest";
            intent1.putExtra("request",request);
            startActivity(intent1);
        }
        else if(view.getId()==R.id.btnpopular){
            Intent intent2=new Intent(this,Main2Activity.class);
            request="popular";
            intent2.putExtra("request",request);
            startActivity(intent2);
        }
        else if (view.getId()==R.id.btnupcoming){
            Intent intent3=new Intent(this,Main2Activity.class);
            request="upcoming";
            intent3.putExtra("request",request);
            startActivity(intent3);
        }
        else if (view.getId()==R.id.btnsearch){
            Intent intent=new Intent(this,Main4Activity.class);
            startActivity(intent);
        }
    }
}