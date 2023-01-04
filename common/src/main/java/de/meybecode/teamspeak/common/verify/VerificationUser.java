package de.meybecode.teamspeak.common.verify;

import java.util.UUID;

public record VerificationUser(UUID uuid, String name, String rankName, String rankColor, int forumRankId) {

}
