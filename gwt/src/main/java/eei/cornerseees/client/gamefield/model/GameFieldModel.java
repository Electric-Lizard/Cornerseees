package eei.cornerseees.client.gamefield.model;

import eei.cornerseees.client.gamefield.model.GameFieldCellModel;
import eei.cornerseees.shared.gamefield.GameField;

/**
 * Created by username on 8/15/15.
 */
public class GameFieldModel implements GameField {
    protected GameFieldCellModel[][] cells;

    public GameFieldModel() {}

    public GameFieldModel(GameFieldCellModel[][] cells) {
        this.cells = cells;
    }

    @Override
    public GameFieldCellModel[][] getCells() {
        return cells;
    }

    public void setCells(GameFieldCellModel[][] cells) {
        this.cells = cells;
    }
}
