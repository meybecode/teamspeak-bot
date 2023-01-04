package de.meybecode.teamspeak.http.server.controller;

import de.meybecode.teamspeak.common.TeamSpeakLog;
import de.meybecode.teamspeak.http.server.entity.TeamSpeakLogEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public interface TeamSpeakLogController {

    @RequestMapping("/log/get/{id}")
    TeamSpeakLogEntity getTeamSpeakLog(@PathVariable long id);

    @PostMapping("/log/create")
    TeamSpeakLogEntity createTeamSpeakLog(@RequestBody TeamSpeakLog teamSpeakLog);

}
