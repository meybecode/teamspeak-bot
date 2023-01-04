package de.meybecode.teamspeak.http.client;

import lombok.Getter;
import de.meybecode.teamspeak.http.client.service.verify.UserVerifyService;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

@Getter
public class VerifyHttpClient {

    private final UserVerifyService userVerifyService;

    public VerifyHttpClient(final String baseUrl, final String apiKey) {
        final OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(chain ->
                chain.proceed(chain.request().newBuilder().addHeader("X-API-KEY", apiKey).build())).build();
        final Retrofit retrofit = new Retrofit.Builder().baseUrl(baseUrl).client(okHttpClient)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create()).build();
        this.userVerifyService = retrofit.create(UserVerifyService.class);
    }

}
