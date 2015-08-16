package eii.cornerseees.server.core;

import eei.cornerseees.shared.gamefield.GameFieldCell;

/**
 * Created by username on 8/15/15.
 */
public class CornerseeesCell extends GameFieldCell {
    CornerseeesPiece piece;

    public CornerseeesCell() {}

    public CornerseeesCell(CornerseeesPiece piece) {
         this.piece = piece;
     }

    @Override
    public CornerseeesPiece getPiece() {
        return piece;
    }

    public void setPiece(CornerseeesPiece piece) {
        this.piece = piece;
    }
}
