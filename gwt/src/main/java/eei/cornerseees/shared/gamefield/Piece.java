package eei.cornerseees.shared.gamefield;

/**
 * Created by username on 8/15/15.
 */
public class Piece {
    public enum Team {RED, BLUE}
    protected Team team;

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
