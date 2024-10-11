package fr.ipme.geoguessish.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class LoginDTO {
    private String login;
    private String password;
}