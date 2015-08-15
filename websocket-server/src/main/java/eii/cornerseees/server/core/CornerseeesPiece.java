package eii.cornerseees.server.core;

import eei.cornerseees.shared.gamefield.Piece;

/**
 * Created by username on 8/15/15.
 */
public class CornerseeesPiece extends Piece {
    Team team;

    public CornerseeesPiece(Team team) {
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
