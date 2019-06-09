package co.com.ceiba.mobile.pruebadeingreso.rest;

import java.util.List;

import co.com.ceiba.mobile.pruebadeingreso.rest.result.UsersResult;

import retrofit2.Call;
import retrofit2.http.GET;

public interface UsersClient {

    @GET(Endpoints.GET_USERS)
    Call<List<UsersResult>> getUsers();
}
