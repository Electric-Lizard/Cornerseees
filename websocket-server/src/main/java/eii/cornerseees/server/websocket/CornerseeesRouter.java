package eii.cornerseees.server.websocket;

import eei.cornerseees.shared.WSRequest;
import eii.cornerseees.server.websocket.service.GameFieldService;
import eii.cornerseees.server.websocket.messageHandler.MessageHandler;

import javax.websocket.Session;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by username on 8/15/15.
 */
public class CornerseeesRouter {
    protected CornerseeesEndPoint endPoint;
    protected Map<WSRequest.RequestName, MessageHandler> messageHandlers;
    protected GameFieldService gameFieldService;


    public CornerseeesRouter(CornerseeesEndPoint endPoint) {
        setDependencies(endPoint);
        assignMessageHandlers();
    }

    public void handleRequest(WSRequest request, Session session) {

    }

    protected void setDependencies(CornerseeesEndPoint endPoint) {
        this.endPoint = endPoint;
        gameFieldService = new GameFieldService(endPoint);
    }

    protected void assignMessageHandlers() {
        messageHandlers = new HashMap<>();
        messageHandlers.put(WSRequest.RequestName.getGameField, new MessageHandler() {
            @Override
            public void handlerMessage(String message, Session session) {
                gameFieldService.sendGameField(session);
            }
        });
    }
}
