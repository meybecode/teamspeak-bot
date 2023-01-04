package de.meybecode.teamspeak.daemon.config;

import lombok.Getter;

import java.util.List;

@Getter
public class TeamSpeakSupportConfigGeneric {

    private int supportQueueChannelId;
    private int supportGroupId;
    private List<Integer> supportChannelIds;
    private String supportChannelNameWhenOpen;
    private String supportChannelNameWhenClosed;
    private String supportChannelPasswordWhenClosed;

}
