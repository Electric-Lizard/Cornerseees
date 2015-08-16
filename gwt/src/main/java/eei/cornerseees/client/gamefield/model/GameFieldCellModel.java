package eei.cornerseees.client.gamefield.model;

import eei.cornerseees.shared.gamefield.GameFieldCell;

/**
 * Created by username on 8/15/15.
 */
public class GameFieldCellModel implements GameFieldCell {
    protected PieceModel piece;

    public GameFieldCellModel() {}

    public GameFieldCellModel(PieceModel piece) {
        this.piece = piece;
    }

    @Override
    public PieceModel getPiece() {
        return piece;
    }

    public void setPiece(PieceModel piece) {
        this.piece = piece;
    }
}
