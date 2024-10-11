package fr.ipme.geoguessish.service.interfaces;

import fr.ipme.geoguessish.dto.RegisterDTO;
import fr.ipme.geoguessish.entity.User;

import java.security.Principal;

public interface UserServiceInterface {

    User show(Principal principal);

    User register(RegisterDTO registerDTO);

    User findByEmail(String email);

}
