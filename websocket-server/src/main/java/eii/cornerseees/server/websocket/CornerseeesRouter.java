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

    public void handleRequest(String message, Session session) throws Exception {
        WSRequest.RequestName requestName = RequestSerializer.getRequestName(message);
        MessageHandler messageHandler = messageHandlers.get(requestName);
        if (messageHandler == null) {
            throw new Exception("Request not found");
        }
        messageHandler.handleMessage(message, session);
    }

    protected void setDependencies(CornerseeesEndPoint endPoint) {
        this.endPoint = endPoint;
        gameFieldService = new GameFieldService(endPoint);
    }

    protected void assignMessageHandlers() {
        messageHandlers = new HashMap<>();

        messageHandlers.put(WSRequest.RequestName.getGameField,
                (encodedData, session) -> gameFieldService.sendGameField(session));
    }
}
