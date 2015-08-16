package eei.cornerseees.client.gamefield.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.*;
import eei.cornerseees.client.event.ItemDropHandler;
import eei.cornerseees.client.gamefield.model.GameFieldCellModel;
import eei.cornerseees.client.gamefield.model.PieceModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by username on 8/9/15.
 */
public class Cell extends Composite {
    @UiTemplate("template/Cell.ui.xml")
    protected interface CellUiBinder extends UiBinder<SimplePanel, Cell> {}
    protected static CellUiBinder ourUiBinder = GWT.create(CellUiBinder.class);

    @UiField protected SimplePanel root;
    protected Item item;

    protected GameFieldCellModel cellModel;
    protected List<ItemDropHandler> itemDropHandlers = new ArrayList<>();

    public Cell(GameFieldCellModel cellModel) {
        this.cellModel = cellModel;
        initWidget(ourUiBinder.createAndBindUi(this));
        PieceModel piece = cellModel.getPiece();
        if (piece != null) {
            setItem(new Item(piece, this));
        }
    }

    public void setItem(Item item) {
        if (this.item != null) {
            root.remove(this.item);
        }

        root.add(item);
        item.setHolderCell(this);
        this.item = item;
    }

    public Item getItem() {
        return item;
    }

    public void itemDropped(Item item) {
        //trigger event
        for (ItemDropHandler itemDropHandler : itemDropHandlers) {
            itemDropHandler.itemDropped(this, item);
        }
    }

    public void addItemDropHandler(ItemDropHandler itemDropHandler) {
        itemDropHandlers.add(itemDropHandler);
    }

    public void removeItemDropHandler(ItemDropHandler itemDropHandler) {
        itemDropHandlers.remove(itemDropHandler);
    }
}