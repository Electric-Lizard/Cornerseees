package eei.cornerseees.shared.gamefield;

import eei.cornerseees.shared.JSONSerializable;

/**
 * Created by username on 8/15/15.
 */
public class GameField implements JSONSerializable {
    protected GameFieldCell[][] cells;

    public GameFieldCell[][] getCells() {
        return cells;
    }

    public void setCells(GameFieldCell[][] cells) {
        this.cells = cells;
    }
}
