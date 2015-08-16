package eei.cornerseees.client.gamefield.view;

import com.allen_sauer.gwt.dnd.client.DragContext;
import com.allen_sauer.gwt.dnd.client.drop.AbstractDropController;

/**
 * Created by username on 8/9/15.
 */
public class CellDropController extends AbstractDropController {
    Cell targetCell;
    public CellDropController(Cell dropTarget) {
        super(dropTarget);
        targetCell = dropTarget;
    }

    @Override
    public void onDrop(DragContext context) {
        super.onDrop(context);
        targetCell.itemDropped((Item) context.draggable);
    }
}
