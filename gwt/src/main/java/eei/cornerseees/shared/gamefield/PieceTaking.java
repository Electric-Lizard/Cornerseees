package eei.cornerseees.shared.gamefield;

import eei.cornerseees.shared.JSONSerializable;

/**
 * Created by username on 8/16/15.
 */
public class PieceTaking implements JSONSerializable {
    protected int[] cellCoordinates;

    public PieceTaking() {}

    public PieceTaking(int[] cellCoordinates) {
        this.cellCoordinates = cellCoordinates;
    }

    public int[] getCellCoordinates() {
        return cellCoordinates;
    }
    public void setCellCoordinates(int[] cellCoordinates) {
        this.cellCoordinates = cellCoordinates;
    }
}
