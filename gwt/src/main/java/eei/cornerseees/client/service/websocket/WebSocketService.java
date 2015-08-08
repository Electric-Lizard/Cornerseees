package eei.cornerseees.client.service.websocket;


import com.google.gwt.core.client.GWT;
import com.google.gwt.logging.client.ConsoleLogHandler;
import com.google.gwt.typedarrays.shared.ArrayBuffer;
import com.google.gwt.user.client.Window;
import eei.cornerseees.client.event.ActionHandler;
import org.realityforge.gwt.websockets.client.WebSocket;
import org.realityforge.gwt.websockets.client.WebSocketListener;

import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

/**
 * Created by username on 8/6/15.
 */
public class WebSocketService implements WebSocketListener {
    WebSocket socket = WebSocket.newWebSocketIfSupported();
    Logger console = Logger.getLogger("websocket");
    ActionHandler onOpenHandler;

    public void establish() {
        String webSocketURL = "ws://127.0.0.1:8080/textStream";
        socket.setListener(this);
        socket.connect(webSocketURL);
    }

    public void onOpen(ActionHandler onOpenHandler) {
        this.onOpenHandler = onOpenHandler;
    }

    @Override
    public void onOpen(WebSocket webSocket) {
        console.log(new LogRecord(Level.INFO, "Socket opened"));
        Window.alert("OPENED!");

        if (onOpenHandler != null) {
            onOpenHandler.doAction();
        }
    }

    @Override
    public void onClose(WebSocket webSocket, boolean b, int i, String s) {
        console.log(new LogRecord(Level.INFO, "Socket closed"));
    }

    @Override
    public void onMessage(WebSocket webSocket, String s) {
        console.log(new LogRecord(Level.INFO, "Socket message"));
        Window.alert(s);
    }

    @Override
    public void onMessage(WebSocket webSocket, ArrayBuffer arrayBuffer) {
        console.log(new LogRecord(Level.INFO, "Socket byteMessage"));
    }

    @Override
    public void onError(WebSocket webSocket) {
        console.log(new LogRecord(Level.INFO, "Socket error"));
    }
}
