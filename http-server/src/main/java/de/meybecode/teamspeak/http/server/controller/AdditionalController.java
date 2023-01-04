package de.meybecode.teamspeak.http.server.controller;

import de.meybecode.teamspeak.common.Additional;
import de.meybecode.teamspeak.http.server.entity.AdditionalEntity;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Cacheable
@RestController
public interface AdditionalController {

    @RequestMapping("/additional/get/{id}")
    AdditionalEntity getAdditional(@PathVariable long id);

    @RequestMapping("/additional/user/{userId}")
    AdditionalEntity getAdditionalByUserId(@PathVariable long userId);

    @PostMapping("/additional/create")
    AdditionalEntity createAdditional(@RequestBody Additional additional);

    @RequestMapping("/additional/list")
    List<AdditionalEntity> getAllAdditions();
}
