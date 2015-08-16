package eii.cornerseees.server.websocket.service;

import eei.cornerseees.shared.WSRequest;
import eii.cornerseees.server.core.CornerseeesGameField;
import eei.cornerseees.shared.gamefield.GameField;
import eii.cornerseees.server.websocket.CornerseeesEndPoint;

import javax.websocket.Session;

/**
 * Created by username on 8/15/15.
 */
public class GameFieldService {
    CornerseeesEndPoint endPoint;
    GameField gameField;

    public GameFieldService(CornerseeesEndPoint endPoint) {
        this.endPoint = endPoint;
        resetGameField();
    }

    public void sendGameField(Session session) {
        WSRequest request = new WSRequest(WSRequest.RequestName.gameField, gameField);
        endPoint.sendRequest(request, session);
    }
    protected void resetGameField() {
        gameField = new CornerseeesGameField(10);
    }
}
