package eei.cornerseees.client.event;

import eei.cornerseees.client.gamefield.view.Cell;
import eei.cornerseees.client.gamefield.view.Item;

/**
 * Created by username on 8/16/15.
 */
public interface ItemDropHandler {
    void itemDropped(Cell acceptingCell, Item droppedItem);
}
