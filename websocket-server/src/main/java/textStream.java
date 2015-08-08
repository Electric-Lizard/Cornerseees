import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 * Created by username on 8/6/15.
 */
@ServerEndpoint("/textStream")
public class textStream {
    @OnOpen
    public void onOpen(Session session) {
        session.getAsyncRemote().sendText("Ohay!");
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        session.getAsyncRemote().sendText("Hear you! It was: " + message);
    }
}
