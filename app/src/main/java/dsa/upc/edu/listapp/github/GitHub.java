package dsa.upc.edu.listapp.github;

import java.util.List;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GitHub {

    //String URL = "https://api.github.com/";

    @GET("/users/{username}/followers")
    Call<List<Follower>> followers(@Path("username") String username);


    @GET("/users/{username}")
    Call<User> user(@Path("username") String username);

    /*Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();*/
}