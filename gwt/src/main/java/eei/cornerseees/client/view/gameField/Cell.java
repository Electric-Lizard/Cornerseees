package eei.cornerseees.client.view.gameField;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.*;
import eei.cornerseees.shared.gamefield.GameFieldCell;
import eei.cornerseees.shared.gamefield.Piece;

/**
 * Created by username on 8/9/15.
 */
public class Cell extends Composite {
    @UiTemplate("template/Cell.ui.xml")
    protected interface CellUiBinder extends UiBinder<SimplePanel, Cell> {}
    protected static CellUiBinder ourUiBinder = GWT.create(CellUiBinder.class);

    @UiField protected SimplePanel root;
    protected Item item;

    protected GameFieldCell cellModel;

    public Cell(GameFieldCell cellModel) {
        this.cellModel = cellModel;
        initWidget(ourUiBinder.createAndBindUi(this));
        Piece piece = cellModel.getPiece();
        if (piece != null) {
            setItem(new Item(piece));
        }
    }

    public void setItem(Item item) {
        if (item != null) {
            root.remove(item);
        }

        root.add(item);
        this.item = item;
    }

    public Item getItem() {
        return item;
    }
}