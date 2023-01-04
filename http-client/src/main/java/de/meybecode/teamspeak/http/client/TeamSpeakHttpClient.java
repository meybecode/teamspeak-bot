package de.meybecode.teamspeak.http.client;

import lombok.Getter;
import de.meybecode.teamspeak.http.client.service.AdditionalService;
import de.meybecode.teamspeak.http.client.service.LogUserService;
import de.meybecode.teamspeak.http.client.service.TeamSpeakActionService;
import de.meybecode.teamspeak.http.client.service.TeamSpeakLogService;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

@Getter
public class TeamSpeakHttpClient {

    private final LogUserService logUserService;
    private final AdditionalService additionalService;
    private final TeamSpeakLogService teamSpeakLogService;
    private final TeamSpeakActionService teamSpeakActionService;

    public TeamSpeakHttpClient(final String baseUrl, final String apiKey) {
        final OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(chain ->
                chain.proceed(chain.request().newBuilder().addHeader("X-API-KEY", apiKey).build())).build();
        final Retrofit retrofit = new Retrofit.Builder().baseUrl(baseUrl).client(okHttpClient)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create()).build();
        this.logUserService = retrofit.create(LogUserService.class);
        this.additionalService = retrofit.create(AdditionalService.class);
        this.teamSpeakLogService = retrofit.create(TeamSpeakLogService.class);
        this.teamSpeakActionService = retrofit.create(TeamSpeakActionService.class);
    }
}
