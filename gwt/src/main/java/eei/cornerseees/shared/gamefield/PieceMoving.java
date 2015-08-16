package eei.cornerseees.shared.gamefield;

import eei.cornerseees.shared.JSONSerializable;

/**
 * Created by username on 8/16/15.
 */
public class PieceMoving implements JSONSerializable {
    protected int[] givingCellCoordinates;
    protected int[] acceptingCellCoordinates;

    public PieceMoving() {}

    public PieceMoving(int[] givingCellCoordinates, int[] acceptingCellCoordinates) {
        this.givingCellCoordinates = givingCellCoordinates;
        this.acceptingCellCoordinates = acceptingCellCoordinates;
    }

    public int[] getGivingCellCoordinates() {
        return givingCellCoordinates;
    }

    public void setGivingCellCoordinates(int[] givingCellCoordinates) {
        this.givingCellCoordinates = givingCellCoordinates;
    }

    public int[] getAcceptingCellCoordinates() {
        return acceptingCellCoordinates;
    }

    public void setAcceptingCellCoordinates(int[] acceptingCellCoordinates) {
        this.acceptingCellCoordinates = acceptingCellCoordinates;
    }
}
