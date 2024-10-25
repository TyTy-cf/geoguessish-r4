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
public class Round {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(JsonViews.RoundShow.class)
    private Long id;

    @Column(nullable = false)
    @JsonView(JsonViews.RoundMinimalView.class)
    private Integer points;

    @Column(nullable = false)
    @JsonView(JsonViews.RoundMinimalView.class)
    private Integer time;

    @Column(nullable = false)
    @JsonView(JsonViews.RoundMinimalView.class)
    private Long distance;

    @Column(nullable = false)
    @JsonView(JsonViews.RoundShow.class)
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Coordinate origin;

    @ManyToOne
    private Coordinate selected;

    @ManyToOne
    private Game game;

}