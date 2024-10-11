package fr.ipme.geoguessish.entity;

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
    private String id;

    @Column(nullable = false)
    private Integer maximumTime;

    @Column(nullable = false)
    private Boolean hasMove;

    @Column(nullable = false)
    private Boolean hasPan;

    @Column(nullable = false)
    private Boolean hasZoom;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private Integer nbRounds;

    @ManyToOne
    private Map map;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "game")
    private List<Round> rounds = new ArrayList<>();

}