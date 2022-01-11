package dsa.upc.edu.listapp;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dsa.upc.edu.listapp.github.GitHub;
import dsa.upc.edu.listapp.github.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CredentialsActivity extends AppCompatActivity{

    private static String username;
    static final String URL = "https://api.github.com/";



    TextView usernameEditText;

    Context context;
    SharedPreferences sharedPref;

    GitHub API;

    public void createAPI(){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        API = retrofit.create(GitHub.class);
    }

    @Override
    protected  void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.credentials);
        //context.this;
        sharedPref = getSharedPreferences("userlogged", context.MODE_PRIVATE);

        Intent intent = getIntent();
        usernameEditText = findViewById(R.id.usernameEditText);

        createAPI();
    }
    public void fetch_click(View view){

        username = usernameEditText.getText().toString();
        doFetchCall(username);
    }
    public void track_click(View view){

    }
    public void doFetchCall(String username){

        Call<User> call = API.user(username);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Log.i("username", "" + response.code());

                if(response.code() != 404){
                    User res = response.body();
                    openMainActivity();
                }
                else{
                    String text = "User not found, try again!";
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                t.printStackTrace();
                Context context = getApplicationContext();
                String text = "Error";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        });
    }
    public void openMainActivity(){
        Intent intent = new Intent(this, SplashScreen.class);
        this.startActivity(intent);
    }

    public static String getUsername() {
        return username;
    }
}
