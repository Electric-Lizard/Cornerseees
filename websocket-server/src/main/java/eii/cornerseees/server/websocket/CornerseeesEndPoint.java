package eii.cornerseees.server.websocket;

import eei.cornerseees.shared.WSRequest;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by username on 8/6/15.
 */
@ServerEndpoint("/textStream")
public class CornerseeesEndPoint {
    protected List<Session> sessionList = new ArrayList<Session>();
    protected CornerseeesRouter router = new CornerseeesRouter(this);

    public CornerseeesEndPoint() {

    }

    @OnOpen
    public void openSession(Session session) {
        sessionList.add(session);
        //session.getAsyncRemote().sendString(textService.getText());
    }

    @OnMessage
    public void callReference(String message, Session session) throws NoSuchMethodException, InvocationTargetException,
            IllegalAccessException {
        WSRequest request = RequestSerializer.deserialize(message, WSRequest.class);
        router.handleRequest(request, session);
    }

    @OnClose
    public void closeSession(Session session, CloseReason closeReason) throws IOException {
        removeSession(session);
    }

    @OnError
    public void handleConnectionError(Session session, Throwable error) {
        error.printStackTrace();
    }

    public void sendRequest(WSRequest request) {
        sendRequest(request, sessionList.toArray(new Session[sessionList.size()]));
    }
    public void sendRequest(WSRequest request, Session... sessions) {
        String encodedRequest = RequestSerializer.serialize(request);
        sendString(encodedRequest, sessions);
    }

    protected void sendString(String string, Session[] sessions) {
        for (Session session : sessions) {
            if (session.isOpen()) {
                session.getAsyncRemote().sendText(string);
            } else {

            }
        }
    }
    protected void removeSession(Session session) throws IOException {
        if (session.isOpen()) {
            session.close();
        }
        sessionList.remove(session);
    }
}
