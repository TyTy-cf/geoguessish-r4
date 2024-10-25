package fr.ipme.geoguessish.json_views;

public class JsonViews {

    public interface UserShow {}

    public interface MapMinimalView {}
    public interface MapShow extends MapMinimalView {}
    public interface MapList extends MapMinimalView {}

    public interface GameMinimalView extends MapMinimalView {}
    public interface GameShow extends
            GameMinimalView,
            RoundMinimalView {}
    public interface GameList extends GameMinimalView {}

    public interface RoundMinimalView {}
    public interface RoundShow extends
            RoundMinimalView,
            CoordinateShow {}

    public interface CoordinateShow {}

}
