package eii.cornerseees.server.websocket.messageHandler;

import javax.websocket.Session;

/**
 * Created by username on 8/15/15.
 */
public interface MessageHandler {
    void handlerMessage (String message, Session session);
}
