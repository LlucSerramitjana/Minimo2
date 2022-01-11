package dsa.upc.edu.listapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class ErrorActivity extends AppCompatActivity {
    Context context;
    SharedPreferences sharedPref;
    @Override
    protected  void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.error_screen);
        //context.this;
        sharedPref = getSharedPreferences("userlogged", context.MODE_PRIVATE);

        Intent intent = getIntent();
    }
    public void back(View view)
    {
        openMainActivity();
    }
    public void openMainActivity(){
        Intent intent = new Intent(this, CredentialsActivity.class);
        this.startActivity(intent);
    }
}
