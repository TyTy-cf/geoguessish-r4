package fr.ipme.geoguessish.custom_response;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class JwtResponse {
    private String token;
}