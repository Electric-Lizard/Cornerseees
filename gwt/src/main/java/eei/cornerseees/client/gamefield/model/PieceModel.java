package eei.cornerseees.client.gamefield.model;

/**
 * Created by username on 8/15/15.
 */
public class PieceModel implements eei.cornerseees.shared.gamefield.Piece {
    protected Team team;

    public PieceModel() {}

    public PieceModel(Team team) {
        this.team = team;
    }

    @Override
    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
