package de.meybecode.teamspeak.http.server.controller;

import de.meybecode.teamspeak.common.TeamSpeakAction;
import de.meybecode.teamspeak.http.server.entity.TeamSpeakActionEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public interface TeamSpeakActionController {

    @RequestMapping("/action/get/{id}")
    TeamSpeakActionEntity getAction(@PathVariable long id);

    @PostMapping("/action/create")
    TeamSpeakActionEntity createAction(@RequestBody TeamSpeakAction teamSpeakAction);

    @RequestMapping("/action/list")
    List<TeamSpeakActionEntity> getAllActions();

}
