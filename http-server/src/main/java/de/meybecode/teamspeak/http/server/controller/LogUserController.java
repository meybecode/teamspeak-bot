package de.meybecode.teamspeak.http.server.controller;

import de.meybecode.teamspeak.common.LogUser;
import de.meybecode.teamspeak.http.server.entity.LogUserEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public interface LogUserController {

    @RequestMapping("/user/get/{id}")
    LogUserEntity getLogUser(@PathVariable long id);

    @PostMapping("/user/create")
    LogUserEntity createLogUser(@RequestBody LogUser logUser);

}
