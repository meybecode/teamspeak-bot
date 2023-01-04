package de.meybecode.teamspeak.common.verify;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Verification {
    private UUID player;
    private String service;
    private String code;
    private Status status;
    private String id;
    private long expirationTime;

    public boolean isExpired() {
        return expirationTime < System.currentTimeMillis();
    }

    public enum Status {
        VERIFY_CODE,
        VERIFIED
    }
}
