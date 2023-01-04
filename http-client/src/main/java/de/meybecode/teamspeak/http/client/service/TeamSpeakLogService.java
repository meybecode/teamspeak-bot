package de.meybecode.teamspeak.http.client.service;

import de.meybecode.teamspeak.common.TeamSpeakLog;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface TeamSpeakLogService {

    @GET("log/get/{id}")
    Call<TeamSpeakLog> getLog(@Path("id") long id);

    @POST("log/create")
    Call<TeamSpeakLog> createLog(@Body TeamSpeakLog teamSpeakLog);

}
