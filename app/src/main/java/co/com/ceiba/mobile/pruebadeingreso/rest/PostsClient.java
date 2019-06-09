package co.com.ceiba.mobile.pruebadeingreso.rest;

import java.util.List;

import co.com.ceiba.mobile.pruebadeingreso.rest.result.PostResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PostsClient {

    @GET(Endpoints.GET_POSTS)
    Call<List<PostResult>> getPosts();

    @GET(Endpoints.GET_POSTS)
    Call<List<PostResult>> getPostsByUser(@Query("userId") int userId);
}
