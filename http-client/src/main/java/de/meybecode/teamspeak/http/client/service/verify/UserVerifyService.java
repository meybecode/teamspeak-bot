package de.meybecode.teamspeak.http.client.service.verify;

import de.meybecode.teamspeak.common.verify.Verification;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

import java.util.List;
import java.util.UUID;

public interface UserVerifyService {

    @GET("verification/teamspeak/{id}")
    Call<Verification> findVerification(@Path("id") String id);

    @GET("user/{uuid}/verifications")
    Call<List<Verification>> getVerifications(@Path("uuid") UUID uuid);

}
