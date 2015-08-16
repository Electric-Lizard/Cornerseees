package eii.cornerseees.server.core.cornerseees;

import eei.cornerseees.shared.gamefield.Piece;

/**
 * Created by username on 8/15/15.
 */
public class CornerseeesPiece implements Piece {
    Team team;

    public CornerseeesPiece(Team team) {
        this.team = team;
    }

    @Override
    public Team getTeam() {
        return null;
    }
}
