package eei.cornerseees.server.service;

import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 * Created by username on 8/8/15.
 */

@ServerEndpoint(value = "/textStream")
public class TextStream {
    boolean isSome = false;
    @OnOpen
    public void onOpen(Session session) {
        isSome = true;
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        isSome = true;
    }
}
