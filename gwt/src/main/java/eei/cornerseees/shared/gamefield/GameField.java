package eei.cornerseees.shared.gamefield;

import eei.cornerseees.shared.JSONSerializable;

/**
 * Created by username on 8/16/15.
 */
public interface GameField extends JSONSerializable {
    GameFieldCell[][] getCells();
}
