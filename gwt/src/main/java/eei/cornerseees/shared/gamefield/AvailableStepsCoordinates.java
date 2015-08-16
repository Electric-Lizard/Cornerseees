package eei.cornerseees.shared.gamefield;

import java.util.Set;

/**
 * Created by username on 8/16/15.
 */
public class AvailableStepsCoordinates extends GameFieldCoordinates {
    protected PieceTaking pieceTaking;

    public AvailableStepsCoordinates() {}

    public AvailableStepsCoordinates(Set<int[]> coordinates, PieceTaking pieceTaking) {
        super(coordinates);
        this.pieceTaking = pieceTaking;
    }

    public PieceTaking getPieceTaking() {
        return pieceTaking;
    }

    public void setPieceTaking(PieceTaking pieceTaking) {
        this.pieceTaking = pieceTaking;
    }
}
