package fr.ipme.geoguessish.entity;

import com.fasterxml.jackson.annotation.JsonView;
import fr.ipme.geoguessish.json_views.JsonViews;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JsonView(JsonViews.GameMinimalView.class)
    private String id;

    @Column(nullable = false)
    @JsonView(JsonViews.GameShow.class)
    private Integer maximumTime;

    @Column(nullable = false)
    @JsonView(JsonViews.GameShow.class)
    private Boolean hasMove;

    @Column(nullable = false)
    @JsonView(JsonViews.GameShow.class)
    private Boolean hasPan;

    @Column(nullable = false)
    @JsonView(JsonViews.GameShow.class)
    private Boolean hasZoom;

    @Column(nullable = false)
    @JsonView(JsonViews.GameMinimalView.class)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    @JsonView(JsonViews.GameMinimalView.class)
    private Integer nbRounds;

    @ManyToOne
    @JsonView(JsonViews.GameMinimalView.class)
    private Map map;

    @ManyToOne
    @JsonView(JsonViews.GameMinimalView.class)
    private User user;

    @OneToMany(mappedBy = "game")
    @JsonView(JsonViews.GameShow.class)
    private List<Round> rounds = new ArrayList<>();

    @JsonView(JsonViews.GameMinimalView.class)
    public int getTotalPoints() {
        int total = 0;
        for (Round round: rounds) {
            total += round.getPoints();
        }
        return total;
    }

}