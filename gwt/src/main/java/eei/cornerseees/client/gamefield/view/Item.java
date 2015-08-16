package eei.cornerseees.client.gamefield.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.InlineHTML;
import com.google.gwt.user.client.ui.Label;
import eei.cornerseees.client.gamefield.model.PieceModel;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by username on 8/9/15.
 */
public class Item extends Composite {

    @UiTemplate("template/Item.ui.xml")
    protected interface ItemUiBinder extends UiBinder<InlineHTML, Item> {}
    protected static ItemUiBinder ourUiBinder = GWT.create(ItemUiBinder.class);
    protected static Map<PieceModel.Team, String> teamColors;
    static {
        teamColors = new HashMap<>();
        teamColors.put(PieceModel.Team.BLUE, "blue");
        teamColors.put(PieceModel.Team.RED, "red");
    }

    @UiField protected InlineHTML root;

    protected PieceModel piece;
    protected Cell holderCell;

    public Item(PieceModel piece, Cell holderCell) {
        this.piece = piece;
        this.holderCell = holderCell;
        initWidget(ourUiBinder.createAndBindUi(this));
        root.setText("\u25C9");

        root.getElement().getStyle().setColor(teamColors.get(piece.getTeam()));
    }

    public Cell getHolderCell() {
        return holderCell;
    }

    public void setHolderCell(Cell holderCell) {
        this.holderCell = holderCell;
    }

    public Label getRoot() {
        return root;
    }
}