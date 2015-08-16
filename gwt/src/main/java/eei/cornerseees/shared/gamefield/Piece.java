package eei.cornerseees.shared.gamefield;

/**
 * Created by username on 8/16/15.
 */
public interface Piece {
    Team getTeam();

    public enum Team {RED, BLUE}
}
