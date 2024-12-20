package fr.ipme.geoguessish.controller;

import com.fasterxml.jackson.annotation.JsonView;
import fr.ipme.geoguessish.custom_response.JwtResponse;
import fr.ipme.geoguessish.dto.LoginDTO;
import fr.ipme.geoguessish.dto.RegisterDTO;
import fr.ipme.geoguessish.entity.User;
import fr.ipme.geoguessish.json_views.JsonViews;
import fr.ipme.geoguessish.security.JwtAuthenticatorService;
import fr.ipme.geoguessish.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class SecurityRestController {

    private UserService userService;
    private JwtAuthenticatorService jwtAuthenticatorService;

    @PostMapping("/api/register")
    @JsonView(JsonViews.UserShow.class)
    public User register(@Valid @RequestBody RegisterDTO registerDTO) {
        return userService.register(registerDTO);
    }

    @PostMapping("/api/login")
    public ResponseEntity<JwtResponse> login(@Valid @RequestBody LoginDTO loginDTO) {
        return jwtAuthenticatorService.authenticate(loginDTO);
    }

}