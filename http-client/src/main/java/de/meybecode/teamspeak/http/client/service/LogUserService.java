package de.meybecode.teamspeak.http.client.service;

import de.meybecode.teamspeak.common.LogUser;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface LogUserService {

    @GET("user/get/{id}")
    Call<LogUser> getUser(@Path("id") long id);

    @POST("user/create")
    Call<LogUser> createUser(@Body LogUser logUser);

}
