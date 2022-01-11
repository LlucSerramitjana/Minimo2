package dsa.upc.edu.listapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.Toast;

public class SplashScreen2 extends Activity {
    private final int DURACION_SPLASH = 3000;
    Context context;
    SharedPreferences sharedPref;
    String username;
    String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);


        new Handler().postDelayed(new Runnable() {
            public void run() {
                username = "";
                password= "";

                //getUserLogged(); //Comentar si se quiere pasar por la parte de login y register

                if (username.equals(password)) {
                    Intent intent = new Intent(SplashScreen2.this, ErrorActivity.class);
                    startActivity(intent);
                    finish();
                }
                else {
                    Intent intent = new Intent(SplashScreen2.this, ErrorActivity.class);
                    startActivity(intent);
                    finish();

                }
            };
        }, DURACION_SPLASH);
    }


    public void getUserLogged() {
        SharedPreferences sharedPref = getSharedPreferences("userlogged", Context.MODE_PRIVATE);
        username = sharedPref.getString("name", "No information found");
        password = sharedPref.getString("password", "No information found");
        Toast.makeText(getApplicationContext(), "User found: " + username, Toast.LENGTH_LONG);
    }

}
