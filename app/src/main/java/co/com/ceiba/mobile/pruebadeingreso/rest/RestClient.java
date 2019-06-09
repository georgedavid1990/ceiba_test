package co.com.ceiba.mobile.pruebadeingreso.rest;

import java.util.List;
import java.util.concurrent.TimeUnit;

import co.com.ceiba.mobile.pruebadeingreso.rest.result.UsersResult;
import co.com.ceiba.mobile.pruebadeingreso.rest.result.PostResult;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestClient {

    private UsersClient usersClient;
    private PostsClient postsClient;

    private static RestClient restClient;

    public  static RestClient getRestClient() {
        if (restClient == null) {
            restClient = new RestClient();
        }
        return restClient;
    }

    private RestClient(){
        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .connectTimeout(40, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .build();

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(Endpoints.URL_BASE)
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.
                client(okHttpClient)
                .build();

        usersClient = retrofit.create(UsersClient.class);
        postsClient = retrofit.create(PostsClient.class);
    }

    // Fetch users.
    public Call<List<UsersResult>> getUsers(){
        return usersClient.getUsers();
    }

    // Fetch posts.
    public Call<List<PostResult>> getPosts(){
        return postsClient.getPosts();
    }

    // Fetch posts.
    public Call<List<PostResult>> getPostsByUser(int userId){
        return postsClient.getPostsByUser(userId);
    }
}
