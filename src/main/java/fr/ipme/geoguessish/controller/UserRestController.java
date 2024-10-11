package fr.ipme.geoguessish.controller;

import com.fasterxml.jackson.annotation.JsonView;
import fr.ipme.geoguessish.entity.User;
import fr.ipme.geoguessish.json_views.JsonViews;
import fr.ipme.geoguessish.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@AllArgsConstructor
@RequestMapping("/api/user")
public class UserRestController {

    private UserService userService;

    @GetMapping("/me")
    @JsonView(JsonViews.UserShow.class)
    public User show(Principal principal) {
        return userService.findByEmail(principal.getName());
    }

}