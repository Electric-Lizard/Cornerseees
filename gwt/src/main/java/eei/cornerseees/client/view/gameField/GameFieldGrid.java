package eei.cornerseees.client.view.gameField;

import com.allen_sauer.gwt.dnd.client.PickupDragController;
import com.allen_sauer.gwt.dnd.client.drop.DropController;
import com.google.gwt.core.client.GWT;
import com.google.gwt.logging.client.ConsoleLogHandler;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiFactory;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;

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

    public GameFieldGrid(int rowsCount, int columnsCount) {
        size = new int[] {rowsCount, columnsCount};
        cellList = new Cell[rowsCount][columnsCount];

        initWidget(ourUiBinder.createAndBindUi(this));
        renderCell();
    }

    protected void renderCell() {
        dragController = new PickupDragController(root, false);
        dragController.setBehaviorMultipleSelection(false);

        for (int row = 0; row < size[0]; row++) {
            for (int column = 0; column < size[1]; column++) {
                Cell cell = new Cell();
                grid.setWidget(row, column, cell);
                cellList[row][column] = cell;

                DropController dropController = new CellDropController(cell);
                dragController.registerDropController(dropController);
            }
        }
        style();

        replaceItems(new int[][]{
                {0,0}, {0,1}, {0,2}
        });
    }

    protected void replaceItems(int[][] itemCoordinates) {
        for (int[] itemCoordinate : itemCoordinates) {
            Cell cell = cellList[itemCoordinate[0]][itemCoordinate[1]];
            Item item = new Item();
            cell.setItem(item);
            dragController.makeDraggable(item, item.getRoot());
        }
    }

    protected void style() {

    }

    @UiFactory Grid makeGrid() {
        return new Grid(size[0], size[1]);
    }
}