package eei.cornerseees.client.service.websocket;


import com.google.gwt.core.client.GWT;
import com.google.gwt.typedarrays.shared.ArrayBuffer;
import com.google.gwt.user.client.Window;
import eei.cornerseees.client.event.ActionHandler;
import eei.cornerseees.shared.WSRequest;
import eei.cornerseees.client.RequestSerializer;
import org.realityforge.gwt.websockets.client.WebSocket;
import org.realityforge.gwt.websockets.client.WebSocketListener;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

/**
 * Created by username on 8/6/15.
 */
public class CornerseeesEndpoint implements WebSocketListener {
    WebSocket socket = WebSocket.newWebSocketIfSupported();
    Logger console = Logger.getLogger("websocket");
    List<ActionHandler> onOpenHandlers = new ArrayList<>();
    CornerseeesRouter router = new CornerseeesRouter(this);

    public void establish() {
        String webSocketURL = GWT.getHostPageBaseURL().replace("http", "ws") + "textStream";
        webSocketURL = "ws://127.0.0.1:8082/textStream";//debug
        socket.setListener(this);
        socket.connect(webSocketURL);
    }

    public CornerseeesRouter getRouter() {
        return router;
    }

    protected void sendRequest(WSRequest request) {
        String requestBuffer = RequestSerializer.serialize(request).toString();
        socket.send(requestBuffer);
    }

    public void addOnOpenHandler(ActionHandler onOpenHandler) {
        onOpenHandlers.add(onOpenHandler);
    }

    @Override
    public void onOpen(WebSocket webSocket) {
        console.log(new LogRecord(Level.INFO, "Socket opened"));

        for (ActionHandler actionHandler : onOpenHandlers) {
            actionHandler.doAction();
        }
    }

    @Override
    public void onClose(WebSocket webSocket, boolean b, int i, String s) {
        console.log(new LogRecord(Level.INFO, "Socket closed"));
    }

    @Override
    public void onMessage(WebSocket webSocket, String text) {
        router.handleRequest(text);
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
