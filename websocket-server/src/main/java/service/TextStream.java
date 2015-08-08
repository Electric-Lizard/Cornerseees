package service;

import event.TextChangeHandler;
import logic.TextService;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by username on 8/6/15.
 */
@ServerEndpoint("/textStream")
public class TextStream {
    List<Session> sessionList = new ArrayList<Session>();
    TextService textService = TextService.getInstance();
    public TextStream() {
        textService.onTextChange(new TextChangeHandler() {
            @Override
            public void onTextChange(String text) {
                sendText(text);
            }
        });
    }

    @OnOpen
    public void openSession(Session session) {
        sessionList.add(session);
        session.getAsyncRemote().sendText(textService.getText());
    }

    @OnMessage
    public void saveText(String message, Session session) {
        textService.setText(message);
    }

    @OnClose
    public void closeSession(Session session, CloseReason closeReason) throws IOException {
        removeSession(session);
    }

    @OnError
    public void handleConnectionError(Session session, Throwable error) {
        error.printStackTrace();
    }

    protected void sendText(String text) {
        for (Session session : sessionList) {
            if (session.isOpen()) {
                session.getAsyncRemote().sendText(text);
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
