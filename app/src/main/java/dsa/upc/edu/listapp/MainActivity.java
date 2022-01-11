package dsa.upc.edu.listapp;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import dsa.upc.edu.listapp.github.Contributor;
import dsa.upc.edu.listapp.github.Follower;
import dsa.upc.edu.listapp.github.GitHub;
import dsa.upc.edu.listapp.github.Repos;
import dsa.upc.edu.listapp.github.User;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

// Based on http://www.vogella.com/tutorials/AndroidRecyclerView/article.html
//      and https://zeroturnaround.com/rebellabs/getting-started-with-retrofit-2/

public class MainActivity extends AppCompatActivity {
    private User user;
    private RecyclerView recyclerView;
    private MyAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private SwipeRefreshLayout swipeRefreshLayout;
    static final String URL = "https://api.github.com/";
    private final String TAG = MainActivity.class.getSimpleName();
    GitHub gitHub;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        swipeRefreshLayout = findViewById(R.id.my_swipe_refresh);
        TextView num_repos = (TextView) findViewById(R.id.secondLine2);
        TextView following = (TextView) findViewById(R.id.firstLine2);
        ImageView avatar = (ImageView) findViewById(R.id.icon2);
        final String username = CredentialsActivity.getUsername();
        createGitHubAPI();
        Intent intent = getIntent();


        //num_repos.setText("Repos: 25");
        //following.setText("Following: 10");

        // use this setting to
        // improve performance if you know that changes
        // in content do not change the layout size
        // of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);

        // Set the adapter
        adapter = new MyAdapter();
        recyclerView.setAdapter(adapter);

        doApiCall(swipeRefreshLayout, username);
        doUserCall(username);

        // Manage swipe on items
        ItemTouchHelper.SimpleCallback simpleItemTouchCallback =
                new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
                    @Override
                    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder
                            target) {
                        return false;
                    }
                    @Override
                    public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                        adapter.remove(viewHolder.getAdapterPosition());
                    }
                };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);

        swipeRefreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        doApiCall(swipeRefreshLayout, username);
                    }
                }
        );

    }
    public void setData(User userset) {
        user = userset;
        TextView num_repos = (TextView) findViewById(R.id.secondLine2);
        TextView following = (TextView) findViewById(R.id.firstLine2);
        ImageView avatar = (ImageView) findViewById(R.id.icon2);
        num_repos.setText("Followers: "+user.getFollowing());
        following.setText("Following: " + user.getFollowers());
        Glide.with(getApplicationContext())
                .load(user.getAvatar_url())
                .into(avatar);
    }
    //prova2
    private void doApiCall(final SwipeRefreshLayout mySwipeRefreshLayout, String username) {
        Call<List<Repos>> call = gitHub.repos(username);
        call.enqueue(new Callback<List<Repos>>() {
            @Override
            public void onResponse(Call<List<Repos>> call, Response<List<Repos>> response) {
                // set the results to the adapter
                adapter.setData(response.body());

                if(mySwipeRefreshLayout!=null) mySwipeRefreshLayout.setRefreshing(false);
            }
            @Override
            public void onFailure(Call<List<Repos>> call, Throwable t) {
                if(mySwipeRefreshLayout!=null) mySwipeRefreshLayout.setRefreshing(false);

                String msg = "Error in retrofit: "+t.toString();
                Log.d(TAG,msg);
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG);
            }
        });

    }
    private void doUserCall(String username){
        Call<User> calluser = gitHub.user(username);
        calluser.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                setData(response.body());

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }
    public void createGitHubAPI(){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        gitHub = retrofit.create(GitHub.class);
    }



}
