package fr.ipme.geoguessish.configuration;

import fr.ipme.geoguessish.repository.*;
import fr.ipme.geoguessish.entity.*;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

@Component
public class InitDataLoaderConfig implements CommandLineRunner {

    private final UserRepository userRepository;
    private final CoordinateRepository coordinateRepository;
    private final GameRepository gameRepository;
    private final MapRepository mapRepository;
    private final RoundRepository roundRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    private final Faker faker;

    @Autowired
    public InitDataLoaderConfig(
        UserRepository userRepository,
        CoordinateRepository coordinateRepository,
        GameRepository gameRepository,
        MapRepository mapRepository,
        RoundRepository roundRepository,
        BCryptPasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.coordinateRepository = coordinateRepository;
        this.gameRepository = gameRepository;
        this.mapRepository = mapRepository;
        this.roundRepository = roundRepository;
        this.passwordEncoder = passwordEncoder;
        this.faker = new Faker(Locale.of("fr"));
    }


    @Override
    public void run(String... args) {
        createUsers();
        createMap();
        createCoordinate();
        createGame();
    }

    private void createUsers() {
        List<String> existingEmail = new ArrayList<>();
        List<String> existingNickname = new ArrayList<>();
        if (userRepository.count() != 1000) {
            for (long i = 1L; i <= 1000L; i++) {
                User user = new User();
                String email;
                do {
                    String firstname = faker.name().firstName();
                    String lastname = faker.name().lastName();
                    email = firstname + "." + lastname;
                } while (existingEmail.contains(email));
                existingEmail.add(email);
                user.setCreatedAt(LocalDateTime.now());
                user.setEmail(faker.internet().emailAddress(email.toLowerCase()));
                user.setLevel(getRandomBetween(1, 50));
                String nickname;
                do {
                    nickname = getNickname();
                } while (existingNickname.contains(nickname));
                existingNickname.add(nickname);
                user.setUsername(nickname);
                user.setBirthedAt(generateRandomDate(
                        Instant.now().minusSeconds(999999999)
                                .minusSeconds(999999999)
                                .minusSeconds(999999999)).toLocalDate());
                user.setPassword(passwordEncoder.encode("12345"));
                String roles = "[\"ROLE_USER\"";
                if (i == 1L) {
                    roles += ", \"ROLE_ADMIN\"";
                }
                roles += "]";
                user.setRoles(roles);
                userRepository.save(user);
            }
            userRepository.flush();
        }
    }

    private void createMap() {
        List<String> existing = new ArrayList<>();
        if (mapRepository.count() != 60) {
            for (long i = 1L; i <= 60L; i++) {
                Map map = new Map();
                String name;
                do {
                    name = faker.country().name();
                } while (existing.contains(name));
                existing.add(name);
                map.setName(name);
                map.setCreatedAt(LocalDateTime.now());
                mapRepository.save(map);
            }
            mapRepository.flush();
        }
    }

    private void createCoordinate() {
        List<String> existing = new ArrayList<>();
        if (coordinateRepository.count() != 4000) {
            for (long i = 1L; i <= 4000L; i++) {
                Coordinate coordinate = new Coordinate();
                String latitude;
                String longitude;
                String concat;
                do {
                    latitude = faker.address().latitude();
                    longitude = faker.address().longitude();
                    concat = latitude + "-" + longitude;
                } while (existing.contains(concat));
                existing.add(concat);
                coordinate.setLatitude(latitude);
                coordinate.setLongitude(longitude);
                coordinateRepository.save(coordinate);
            }
            coordinateRepository.flush();
        }
    }

    private void createGame() {
        if (gameRepository.count() != 1500) {
            List<Integer> times = List.of(5, 10, 15, 20, 25, 30, 35, 40, 45, 50, 55, 60, 70, 80, 90, 100, 110, 120, 150, 180, 210, 240, 270, 300);
            List<User> users = userRepository.findAll();
            List<Coordinate> coordinates = coordinateRepository.findAll();
            List<Map> maps = mapRepository.findAll();
            for (long i = 1L; i <= 1500L; i++) {
                Game game = new Game();
                game.setCreatedAt(LocalDateTime.now());
                Random rand = new Random();
                game.setMaximumTime(times.get(rand.nextInt(0, 23)));
                Random random = new Random();
                game.setHasZoom(random.nextBoolean());
                game.setHasPan(random.nextBoolean());
                game.setHasMove(random.nextBoolean());
                game.setNbRounds(5);
                User user = users.get(random.nextInt(0, (users.size() - 1)));
                game.setUser(user);
                game.setMap(maps.get(random.nextInt(0, (maps.size() - 1))));
                gameRepository.save(game);
                for (int j = 0; j < 5; j++) {
                    Round round = new Round();
                    round.setCreatedAt(LocalDateTime.now().plusSeconds(game.getMaximumTime()));
                    round.setDistance(random.nextLong(0, 10000000));
                    round.setTime(random.nextInt(0, game.getMaximumTime()));
                    int points = Math.round(5000 - (((float) round.getDistance() / 10000000) * 5000));
                    round.setPoints(points);
                    round.setGame(game);
                    Coordinate coordinate = coordinates.get(random.nextInt(0, (coordinates.size() - 1)));
                    round.setOrigin(coordinate);
                    if (points == 5000) {
                        round.setSelected(coordinate);
                    } else {
                        round.setSelected(coordinates.get(random.nextInt(0, (coordinates.size() - 1))));
                    }
                    roundRepository.save(round);
                }
            }
            gameRepository.flush();
        }
    }

    private String getNickname() {
        int nb = getRandomBetween(1, 15);
        return switch (nb) {
            case 1 -> faker.warhammerFantasy().heros();
            case 2 -> faker.redDeadRedemption2().majorCharacter();
            case 3 -> faker.minecraft().entityName();
            case 4 -> faker.kaamelott().character();
            case 5 -> faker.zelda().character();
            case 6 -> faker.pokemon().name();
            case 7 -> faker.witcher().character();
            case 8 -> faker.superSmashBros().fighter();
            case 9 -> faker.artist().name();
            case 10 -> faker.breakingBad().character();
            case 11 -> faker.dragonBall().character();
            case 12 -> faker.finalFantasyXIV().character();
            case 13 -> faker.gameOfThrones().character();
            case 14 -> faker.leagueOfLegends().champion();
            case 15 -> faker.starCraft().character();
            default -> faker.worldOfWarcraft().hero();
        };
    }

    private int getRandomBetween(int start, int end) {
        Random rand = new Random();
        return rand.nextInt(1, 50);
    }

    private LocalDateTime generateRandomDate(Instant start) {
        Faker faker = new Faker();
        Instant randomDate = faker.timeAndDate().between(
                start,
                Instant.now());
        return randomDate.atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

}
