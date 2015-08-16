package eei.cornerseees.client.service.websocket;

import eei.cornerseees.client.RequestSerializer;
import eei.cornerseees.shared.WSRequest;
import eei.cornerseees.shared.gamefield.PieceMoving;
import eei.cornerseees.shared.gamefield.PieceTaking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by username on 8/15/15.
 */
public class CornerseeesRouter {
    CornerseeesEndpoint endpoint;
    Map<WSRequest.RequestName, List<MessageHandler>> messageHandlers = new HashMap<>();

    public CornerseeesRouter(CornerseeesEndpoint endpoint) {
        this.endpoint = endpoint;
    }

    public void handleRequest(String encodedRequest) {
        WSRequest.RequestName requestName = RequestSerializer.getRequestName(encodedRequest);
        List<MessageHandler> messageHandlerList = messageHandlers.get(requestName);
        for (MessageHandler messageHandler : messageHandlerList) {
            messageHandler.handleMessage(encodedRequest);
        }
    }



    public void addMessageHandler(WSRequest.RequestName requestName, MessageHandler messageHandler) {
        List<MessageHandler> messageHandlerList = messageHandlers.get(requestName);
        //Create if not exist
        if (messageHandlerList == null) {
            messageHandlerList = new ArrayList<>();
            messageHandlers.put(requestName, messageHandlerList);
        }
        messageHandlerList.add(messageHandler);
    }

    public void removeMessageHandler(WSRequest.RequestName requestName, MessageHandler messageHandler) {
        List<MessageHandler> messageHandlerList = messageHandlers.get(requestName);
        messageHandlerList.remove(messageHandler);
    }

    public void fetchGameField() {
        endpoint.sendRequest(new WSRequest(WSRequest.RequestName.getGameField, null));
    }

    public void movePiece(PieceMoving pieceMoving) {
        endpoint.sendRequest(new WSRequest(WSRequest.RequestName.movePiece, pieceMoving));
    }

    public void pieceTaken(PieceTaking pieceTaking) {
        endpoint.sendRequest(new WSRequest(WSRequest.RequestName.pieceTaken, pieceTaking));
    }
}
