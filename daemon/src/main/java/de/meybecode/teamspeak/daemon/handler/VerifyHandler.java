package de.meybecode.teamspeak.daemon.handler;

import lombok.RequiredArgsConstructor;
import de.meybecode.teamspeak.common.verify.Verification;
import de.meybecode.teamspeak.http.client.service.verify.UserVerifyService;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

@RequiredArgsConstructor
public class VerifyHandler {

    private final UserVerifyService verifyService;

    public CompletableFuture<Verification> getVerificationUser(int databaseId) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return verifyService.findVerification(Integer.toString(databaseId)).execute().body();
            } catch (IOException e) {
                return null;
            }
        });
    }

}
