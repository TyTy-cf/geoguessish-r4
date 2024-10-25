package fr.ipme.geoguessish.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RegisterDTO {

    @Email
    private String email;

    @NotBlank
    private String username;

    @NotBlank
    private String avatar;

    @NotBlank
    private String password;

    @NotBlank
    private String confirmedPassword;

    @Past
    private LocalDate birthedAt;
}