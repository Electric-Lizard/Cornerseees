package eei.cornerseees.client.service.websocket;

import eei.cornerseees.client.RequestSerializer;
import eei.cornerseees.shared.WSRequest;

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
}
