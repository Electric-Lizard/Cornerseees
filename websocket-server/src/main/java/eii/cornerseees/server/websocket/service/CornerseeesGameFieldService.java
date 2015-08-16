package eii.cornerseees.server.websocket.service;

import eei.cornerseees.shared.WSRequest;
import eei.cornerseees.shared.gamefield.PieceMoving;
import eei.cornerseees.shared.gamefield.PieceTaking;
import eii.cornerseees.server.core.cornerseees.CellNotFoundException;
import eii.cornerseees.server.core.cornerseees.CornerseeesGameField;
import eii.cornerseees.server.core.cornerseees.InvalidPieceMovingException;
import eii.cornerseees.server.core.cornerseees.InvalidPieceTakingException;
import eii.cornerseees.server.websocket.CornerseeesEndPoint;

import javax.websocket.Session;
import java.util.Set;

/**
 * Created by username on 8/15/15.
 */
public class CornerseeesGameFieldService {
    protected CornerseeesGameField gameField;

    public CornerseeesGameFieldService() {
        resetGameField();
    }

    public CornerseeesGameField getGameField() {
        return gameField;
    }

    public void movePiece(PieceMoving pieceMoving) {
        try {
            gameField.movePiece(pieceMoving);
        } catch (CellNotFoundException | InvalidPieceMovingException | InvalidPieceTakingException e) {
            //TODO: send error
            e.printStackTrace();
        }
    }

    public Set<int[]> getAvailableSteps(PieceTaking pieceTaking) throws InvalidPieceTakingException {
        return gameField.getAvailableSteps(pieceTaking);
    }

    protected void resetGameField() {
        gameField = new CornerseeesGameField(10);
    }
}
