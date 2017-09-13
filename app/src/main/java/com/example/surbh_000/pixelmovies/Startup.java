package com.example.surbh_000.pixelmovies;

import android.content.ClipData;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.zip.Inflater;

public class Startup extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener,View.OnClickListener{
    GoogleApiClient client;
    Toolbar toolbar;
    private static final int RC_SIGN_IN=9001;
    private static final String TAG="signInActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startup);
        GoogleSignInOptions op=new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        client=new GoogleApiClient.Builder(this).enableAutoManage(this,this).addApi(Auth.GOOGLE_SIGN_IN_API,op).build();
        SignInButton button=(SignInButton)findViewById(R.id.signgoogle);
        button.setOnClickListener(this);
        toolbar=(Toolbar)findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setSubtitle("Login");
        getSupportActionBar().setIcon(R.drawable.ic_toolbar);
        getSupportActionBar().setTitle("Welcome");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item1: Toast.makeText(this,"Option 1 selected",Toast.LENGTH_LONG).show();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.signgoogle){
            Intent signInIntent=Auth.GoogleSignInApi.getSignInIntent(client);
            startActivityForResult(signInIntent,RC_SIGN_IN);

        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==RC_SIGN_IN){
            GoogleSignInResult result=Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if(result.isSuccess()){
                GoogleSignInAccount account=result.getSignInAccount();
                Toast.makeText(this,"Hello "+account.getDisplayName()+" !",Toast.LENGTH_LONG).show();
                Intent intent=new Intent(this,MainActivity.class);
                startActivity(intent);
            }
        }

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.d(TAG,"onConnectionFailed"+connectionResult);
    }
}


