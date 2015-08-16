package eei.cornerseees.client.view.gameField;

import com.allen_sauer.gwt.dnd.client.PickupDragController;
import com.allen_sauer.gwt.dnd.client.drop.DropController;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiFactory;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.*;
import eei.cornerseees.shared.gamefield.GameField;
import eei.cornerseees.shared.gamefield.GameFieldCell;

/**
 * Created by username on 8/9/15.
 */
public class GameFieldGrid extends Composite {
    @UiTemplate("template/GameFieldGrid.ui.xml")
    interface GameFieldGridUiBinder extends UiBinder<AbsolutePanel, GameFieldGrid> {}
    private static GameFieldGridUiBinder ourUiBinder = GWT.create(GameFieldGridUiBinder.class);

    @UiField Grid grid;
    @UiField AbsolutePanel root;

    final protected int[] size;
    protected Cell[][] cellList;
    protected PickupDragController dragController;
    protected GameField gameFieldModel;

    public GameFieldGrid(GameField gameField) {
        this.gameFieldModel = gameField;
        GameFieldCell[][] gameFieldCells = gameField.getCells();
        size = new int[] {gameFieldCells.length, gameFieldCells[0].length};

        cellList = new Cell[size[0]][size[1]];

        initWidget(ourUiBinder.createAndBindUi(this));
        renderCell();
    }

    protected void renderCell() {
        dragController = new PickupDragController(root, false);
        dragController.setBehaviorMultipleSelection(false);

        GameFieldCell[][] gameFieldCells = gameFieldModel.getCells();

        for (int row = 0; row < gameFieldCells.length; row++) {
            for (int column = 0; column < gameFieldCells[row].length; column++) {
                GameFieldCell cellModel = gameFieldCells[row][column];
                Cell cell = new Cell(cellModel);
                Item cellItem = cell.getItem();
                if (cellItem != null) {
                    dragController.makeDraggable(cellItem, cellItem.getRoot());
                }
                grid.setWidget(row, column, cell);
                cellList[row][column] = cell;

                DropController dropController = new CellDropController(cell);
                dragController.registerDropController(dropController);
            }
        }
        style();
    }

    protected void replaceItems(int[][] itemCoordinates) {
        /*for (int[] itemCoordinate : itemCoordinates) {
            Cell cell = cellList[itemCoordinate[0]][itemCoordinate[1]];
            Item item = new Item();
            cell.setItem(item);
            dragController.makeDraggable(item, item.getRoot());
        }*/
    }

    protected void style() {

    }

    @UiFactory Grid makeGrid() {
        return new Grid(size[0], size[1]);
    }
}