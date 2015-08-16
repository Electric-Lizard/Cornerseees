package eei.cornerseees.client.gamefield.view;

import com.allen_sauer.gwt.dnd.client.PickupDragController;
import com.allen_sauer.gwt.dnd.client.drop.DropController;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.DragStartEvent;
import com.google.gwt.event.dom.client.DragStartHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiFactory;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;
import eei.cornerseees.client.event.ItemDropHandler;
import eei.cornerseees.client.event.ItemMoveListener;
import eei.cornerseees.client.event.ItemTakeListener;
import eei.cornerseees.client.gamefield.model.GameFieldModel;
import eei.cornerseees.client.gamefield.model.GameFieldCellModel;
import eei.cornerseees.shared.gamefield.PieceMoving;
import eei.cornerseees.shared.gamefield.PieceTaking;

import java.util.ArrayList;
import java.util.List;

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
    protected GameFieldModel gameFieldModel;
    protected List<ItemMoveListener> itemMoveListeners = new ArrayList<>();
    protected List<ItemTakeListener> itemTakeListeners = new ArrayList<>();

    public GameFieldGrid(GameFieldModel gameField) {
        this.gameFieldModel = gameField;
        GameFieldCellModel[][] gameFieldCells = gameField.getCells();
        size = new int[] {gameFieldCells.length, gameFieldCells[0].length};

        cellList = new Cell[size[0]][size[1]];

        initWidget(ourUiBinder.createAndBindUi(this));
        renderCell();
    }

    public void addItemMoveListeners(ItemMoveListener itemMoveListener) {
        itemMoveListeners.add(itemMoveListener);
    }
    public void removeItemMoveListeners(ItemMoveListener itemMoveListener) {
        itemMoveListeners.remove(itemMoveListener);
    }

    public void addItemTakeListeners(ItemTakeListener itemTakeListener) {
        itemTakeListeners.add(itemTakeListener);
    }
    public void removeItemTakeListeners(ItemTakeListener itemTakeListener) {
        itemTakeListeners.remove(itemTakeListener);
    }

    protected void triggerItemMoveListeners(PieceMoving pieceMoving) {
        for (ItemMoveListener itemMoveListener : itemMoveListeners) {
            itemMoveListener.itemMoved(pieceMoving);
        }
    }
    protected void triggerItemTakeListeners(PieceTaking pieceTaking) {
        for (ItemTakeListener itemTakeListener : itemTakeListeners) {
            itemTakeListener.itemTaken(pieceTaking);
        }
    }

    protected void handleItemMoving(Cell givingCell, Cell acceptingCell) {
        try {
            int[] givingCellCoordinates = getCellCoordinates(givingCell);
            int[] acceptingCellCoordinates = getCellCoordinates(acceptingCell);
            acceptingCell.setItem(givingCell.getItem());
            if (givingCell != acceptingCell) {
                triggerItemMoveListeners(new PieceMoving(givingCellCoordinates, acceptingCellCoordinates));
            }
        } catch (CellNotFoundException e) {
            e.printStackTrace();
        }
    }

    protected void handlerItemTaking(Item item) {
        try {
            int[] cellCoordinates = getCellCoordinates(item.getHolderCell());
            PieceTaking pieceTaking = new PieceTaking(cellCoordinates);
            triggerItemTakeListeners(pieceTaking);
        } catch (CellNotFoundException e) {
            e.printStackTrace();
        }
    }

    protected void renderCell() {
        dragController = new PickupDragController(root, false);
        dragController.setBehaviorMultipleSelection(false);

        GameFieldCellModel[][] gameFieldCells = gameFieldModel.getCells();

        for (int row = 0; row < gameFieldCells.length; row++) {
            for (int column = 0; column < gameFieldCells[row].length; column++) {
                GameFieldCellModel cellModel = gameFieldCells[row][column];
                Cell cell = new Cell(cellModel);
                cell.addItemDropHandler(new ItemDropHandler() {
                    @Override
                    public void itemDropped(Cell acceptingCell, Item droppedItem) {
                        handleItemMoving(droppedItem.getHolderCell(), acceptingCell);
                    }
                });
                final Item cellItem = cell.getItem();
                if (cellItem != null) {
                    dragController.makeDraggable(cellItem, cellItem.getRoot());
                    cellItem.getRoot().addDragStartHandler(new DragStartHandler() {
                        @Override
                        public void onDragStart(DragStartEvent event) {
                            Window.alert("DRAG-START");
                            handlerItemTaking(cellItem);
                        }
                    });
                }
                grid.setWidget(row, column, cell);
                cellList[row][column] = cell;

                DropController dropController = new CellDropController(cell);
                dragController.registerDropController(dropController);
            }
        }
        style();
    }

    protected int[] getCellCoordinates(Cell cell) throws CellNotFoundException {
        for (int row = 0; row < cellList.length; row++) {
            Cell[] storedRow = cellList[row];
            for (int column = 0; column < storedRow.length; column++) {
                if (storedRow[column] == cell) {
                    return new int[]{row, column};
                }
            }
        }
        throw new CellNotFoundException();
    }

    protected void style() {

    }

    @UiFactory Grid makeGrid() {
        return new Grid(size[0], size[1]);
    }
}