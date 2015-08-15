package eii.cornerseees.server.core;

import eei.cornerseees.shared.gamefield.GameField;
import eei.cornerseees.shared.gamefield.Piece;

/**
 * Created by username on 8/15/15.
 */
public class CornerseeesGameField extends GameField {
    protected CornerseeesCell[][] cells;
    protected int size;

    public CornerseeesGameField(int size) {
        this.size = size;
        cells = new CornerseeesCell[size][size];
        reset();
    }

    @Override
    public CornerseeesCell[][] getCells() {
        return cells;
    }

    protected void reset() {
        int teamSize = size/2;

        //Team blue
        for (int row = 0; row < size; row++) {
            for (int column = 0; column < size; column++) {
                CornerseeesCell cell = cells[row][column];
                if (row < teamSize && column < teamSize) {
                    cell.setPiece(new CornerseeesPiece(Piece.Team.BLUE));
                }
            }
        }

        //Team red
        for (int row = size - 1; row >= 0; row--) {
            for (int column = size - 1; column >= 0; column--) {
                CornerseeesCell cell = cells[row][column];
                if (size - row <= teamSize && size - column <= teamSize) {
                    cell.setPiece(new CornerseeesPiece(Piece.Team.RED));
                }
            }
        }
    }
}
