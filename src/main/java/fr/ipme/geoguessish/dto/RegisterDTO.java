package fr.ipme.geoguessish.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RegisterDTO {
    private String email;
    private String username;
    private String avatar;
    private String password;
    private String confirmedPassword;
    private LocalDate birthedAt;
}