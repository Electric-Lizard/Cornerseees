package eei.cornerseees.client.service.websocket;

import com.google.gwt.core.client.GWT;
import com.google.gwt.typedarrays.shared.ArrayBuffer;
import eei.cornerseees.client.service.TextStream;
import eei.cornerseees.shared.event.ActionHandler;
import org.realityforge.gwt.websockets.client.WebSocket;
import org.realityforge.gwt.websockets.client.WebSocketListener;

/**
 * Created by username on 8/8/15.
 */
public class WebSocketService implements WebSocketListener, TextStream {
    final String WEB_SOCKET_URL = "textStream";
    WebSocket socket = WebSocket.newWebSocketIfSupported();
    ActionHandler onOpenHandler;

    public void establish() {
        String webSocketURL = GWT.getModuleBaseURL().replace("http", "ws") + WEB_SOCKET_URL;
        socket.setListener(this);
        socket.connect(webSocketURL);
    }

    public void onOpen(ActionHandler onOpenHandler) {
        this.onOpenHandler = onOpenHandler;
    }

    @Override
    public void onOpen(WebSocket webSocket) {
        if (onOpenHandler != null) {
            onOpenHandler.doAction();
        }
    }

    @Override
    public void onClose(WebSocket webSocket, boolean b, int i, String s) {
    }

    @Override
    public void onMessage(WebSocket webSocket, String s) {
    }

    @Override
    public void onMessage(WebSocket webSocket, ArrayBuffer arrayBuffer) {
    }

    @Override
    public void onError(WebSocket webSocket) {
    }
}
