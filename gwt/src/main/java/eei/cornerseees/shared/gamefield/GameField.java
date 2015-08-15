package eei.cornerseees.shared.gamefield;

import eei.cornerseees.shared.JSONSerializable;

/**
 * Created by username on 8/15/15.
 */
abstract public class GameField implements JSONSerializable {
    public abstract Cell[][] getCells();
}
