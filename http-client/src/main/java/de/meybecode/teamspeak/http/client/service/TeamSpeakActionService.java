package de.meybecode.teamspeak.http.client.service;

import de.meybecode.teamspeak.common.TeamSpeakAction;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

import java.util.List;

public interface TeamSpeakActionService {

    @GET("action/get/{id}")
    Call<TeamSpeakAction> getAction(@Path("id") long id);

    @POST("action/create")
    Call<TeamSpeakAction> createAction(@Body TeamSpeakAction teamSpeakAction);

    @GET("action/list")
    Call<List<TeamSpeakAction>> getAllActions();

}
