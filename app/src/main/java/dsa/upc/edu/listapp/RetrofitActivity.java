package dsa.upc.edu.listapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;

import dsa.upc.edu.listapp.github.Contributor;
import dsa.upc.edu.listapp.github.Follower;
import dsa.upc.edu.listapp.github.GitHub;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetrofitActivity extends AppCompatActivity {

    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GitHub gitHubService = GitHub.retrofit.create(GitHub.class);
                Call<List<Follower>> call = gitHubService.followers("square");

                call.enqueue(new Callback<List<Follower>>() {
                    @Override
                    public void onResponse(Call<List<Follower>> call, Response<List<Follower>> response) {
                        final TextView textView = (TextView) findViewById(R.id.textView);
                        textView.setText(response.body().toString());
                    }
                    @Override
                    public void onFailure(Call<List<Follower>> call, Throwable t) {
                        final TextView textView = (TextView) findViewById(R.id.textView);
                        textView.setText("Something went wrong: " + t.getMessage());
                    }
                });
            }
        });
    }*/
}
