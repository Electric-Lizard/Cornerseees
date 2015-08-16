package eii.cornerseees.server.websocket;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

/**
 * Created by username on 8/6/15.
 */
@ServerEndpoint("/textStream")
public class CornerseeesEndPoint {
    protected CornerseeesRouter cornerseees = CornerseeesRouter.getInstance();

    @OnOpen
    public void openSession(Session session) {
        //session.getAsyncRemote().sendString(textService.getText());
    }

    @OnMessage
    public void callReference(String message, Session session) throws Exception {
        cornerseees.handleRequest(message, session);
    }

    @OnClose
    public void closeSession(Session session, CloseReason closeReason) throws IOException {

    }

    @OnError
    public void handleConnectionError(Session session, Throwable error) {
        error.printStackTrace();
    }
}
