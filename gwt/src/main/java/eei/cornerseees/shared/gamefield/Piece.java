package eei.cornerseees.shared.gamefield;

/**
 * Created by username on 8/15/15.
 */
abstract public class Piece {
    public enum Team {RED, BLUE}
    abstract public Team getTeam();
}
