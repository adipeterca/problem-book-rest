package xyz.problembook.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.problembook.dtos.AvatarDTO;
import xyz.problembook.services.AvatarService;

@RestController
@RequestMapping(path = "avatar")
public class AvatarController {
    private final AvatarService avatarService;

    @Autowired
    public AvatarController(AvatarService avatarService) {
        this.avatarService = avatarService;
    }

    @PostMapping("/")
    public void add(@RequestParam(name = "url") String url) {
        avatarService.add(url);
    }

    @GetMapping("/")
    public AvatarDTO get(@RequestParam(name = "id") Integer id) {
        return avatarService.get(id);
    }
}
