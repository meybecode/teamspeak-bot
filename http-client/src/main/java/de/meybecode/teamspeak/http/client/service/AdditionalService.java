package de.meybecode.teamspeak.http.client.service;

import de.meybecode.teamspeak.common.Additional;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

import java.util.List;

public interface AdditionalService {

    @GET("additional/get/{id}")
    Call<Additional> getAdditional(@Path("id") long id);

    @GET("additional/user/{userId}")
    Call<Additional> getAdditionalByUserId(@Path("userId") long userId);

    @POST("additional/create")
    Call<Additional> createAdditional(@Body Additional additional);

    @GET("additional/list")
    Call<List<Additional>> getAllAdditions();

}
