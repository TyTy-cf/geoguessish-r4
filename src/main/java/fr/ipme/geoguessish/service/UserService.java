package fr.ipme.geoguessish.service;

import fr.ipme.geoguessish.dto.RegisterDTO;
import fr.ipme.geoguessish.entity.User;
import fr.ipme.geoguessish.repository.UserRepository;
import fr.ipme.geoguessish.service.interfaces.UserServiceInterface;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class UserService implements
        UserServiceInterface,
        UserDetailsService {

    private UserRepository userRepository;

    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public User show(Principal principal) {
        return findByEmail(principal.getName());
    }

    @Override
    public User register(RegisterDTO registerDTO) {
        User user = new User();
        user.setUsername(registerDTO.getUsername());
        user.setEmail(registerDTO.getEmail());
        user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
        user.setAvatar(registerDTO.getAvatar());
        user.setBirthedAt(registerDTO.getBirthedAt());
        user.setLevel(1);
        user.setRoles("[\"ROLE_USER\"]");
        user.setCreatedAt(LocalDateTime.now());
        return userRepository.saveAndFlush(user);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Tu étais l'élu !"));
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = findByEmail(username);
        return new org.springframework.security.core.userdetails.User(
            user.getEmail(),
            user.getPassword(),
            user.getAuthorities()
        );
    }
}
