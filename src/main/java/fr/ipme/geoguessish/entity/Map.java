package fr.ipme.geoguessish.entity;

import com.fasterxml.jackson.annotation.JsonView;
import fr.ipme.geoguessish.json_views.JsonViews;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
public class Map {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(JsonViews.MapMinimalView.class)
    private Long id;

    @Column(nullable = false)
    @JsonView(JsonViews.MapMinimalView.class)
    private String name;

    @Column(nullable = false)
    @JsonView(JsonViews.MapShow.class)
    private LocalDateTime createdAt;

}