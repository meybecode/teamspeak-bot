package de.meybecode.teamspeak.daemon.config;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeamSpeakConfigGeneric {

    private String restHost;
    private String apiKey;
    private String teamSpeakHost;
    private String queryName;
    private String queryPassword;
    private int virtualServerId;
    private String queryNickName;
    private String verifyRestHost;
    private String verifyApiKey;

}
