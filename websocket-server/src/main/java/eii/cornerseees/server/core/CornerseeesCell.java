package eii.cornerseees.server.core;

import eei.cornerseees.shared.gamefield.Cell;

/**
 * Created by username on 8/15/15.
 */
public class CornerseeesCell extends Cell {
    CornerseeesPiece piece;

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
