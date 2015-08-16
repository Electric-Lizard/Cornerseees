package eei.cornerseees.client.event;

import eei.cornerseees.shared.gamefield.PieceMoving;

/**
 * Created by username on 8/16/15.
 */
public interface ItemMoveListener {
    void itemMoved(PieceMoving pieceMoving);
}
