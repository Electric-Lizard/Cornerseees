package eei.cornerseees.shared.gamefield;

import java.util.Set;

/**
 * Created by username on 8/16/15.
 */
public class GameFieldCoordinates {
    Set<int[]> coordinates;

    public GameFieldCoordinates() {}
    public GameFieldCoordinates(Set<int[]> coordinates) {
        this.coordinates = coordinates;
    }

    public Set<int[]> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Set<int[]> coordinates) {
        this.coordinates = coordinates;
    }
}
