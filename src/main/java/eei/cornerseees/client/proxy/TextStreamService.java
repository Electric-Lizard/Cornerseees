package eei.cornerseees.client.proxy;


import com.google.gwt.core.client.GWT;
import com.google.gwt.logging.client.ConsoleLogHandler;
import com.google.gwt.typedarrays.shared.ArrayBuffer;
import org.realityforge.gwt.websockets.client.WebSocket;
import org.realityforge.gwt.websockets.client.WebSocketListener;

import java.util.logging.Level;
import java.util.logging.LogRecord;

/**
 * Created by username on 8/6/15.
 */
public class TextStreamService implements WebSocketListener {
    WebSocket socket = WebSocket.newWebSocketIfSupported();
    ConsoleLogHandler console = new ConsoleLogHandler();

    public void establish() {
        String webSocketURL = GWT.getModuleBaseURL().replace("http", "ws") + "textStream";
        socket.setListener(this);
        socket.connect(webSocketURL);
    }

    @Override
    public void onOpen(WebSocket webSocket) {
        console.publish(new LogRecord(Level.INFO, "Socket opened"));
    }

    @Override
    public void onClose(WebSocket webSocket, boolean b, int i, String s) {
        console.publish(new LogRecord(Level.INFO, "Socket closed"));
    }

    @Override
    public void onMessage(WebSocket webSocket, String s) {
        console.publish(new LogRecord(Level.INFO, "Socket message"));
    }

    @Override
    public void onMessage(WebSocket webSocket, ArrayBuffer arrayBuffer) {
        console.publish(new LogRecord(Level.INFO, "Socket byteMessage"));
    }

    @Override
    public void onError(WebSocket webSocket) {
        console.publish(new LogRecord(Level.INFO, "Socket error"));
    }
}
