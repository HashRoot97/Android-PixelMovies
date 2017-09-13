package com.example.surbh_000.pixelmovies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Main4Activity extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        Intent intent=getIntent();
        Button btngo=(Button)findViewById(R.id.btngo);
        btngo.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.btngo){
            EditText editText=(EditText)findViewById(R.id.edit_text_view);
            String request=editText.getText().toString();
            Intent intent1=new Intent(this,Main2Activity.class);
            intent1.putExtra("request",request);
            startActivity(intent1);
        }

    }
}
