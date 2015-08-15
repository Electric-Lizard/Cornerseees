package eei.cornerseees.client.service.websocket;


import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.typedarrays.shared.ArrayBuffer;
import com.google.gwt.user.client.Window;
import eei.cornerseees.client.event.ActionHandler;
import eei.cornerseees.client.event.TextChangeHandler;
import eei.cornerseees.shared.WSRequest;
import eei.cornerseees.client.RequestSerializer;
import eei.cornerseees.client.service.TextStream;
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
public class WebSocketService implements WebSocketListener, TextStream {
    WebSocket socket = WebSocket.newWebSocketIfSupported();
    Logger console = Logger.getLogger("websocket");
    List<ActionHandler> onOpenHandlers = new ArrayList<>();
    List<TextChangeHandler> onChangeHandlers = new ArrayList<>();

    public void establish() {
        String webSocketURL = "ws://127.0.0.1:8080/textStream";
        socket.setListener(this);
        socket.connect(webSocketURL);
    }
    @Override
    public void save(String text) {
        socket.send(text);
    }

    @Override
    public void onChange(TextChangeHandler textChangeHandler) {
        onChangeHandlers.add(textChangeHandler);
    }

    protected void sendRequest(WSRequest request) {
        //Window.alert(request.toString());
        String requestBuffer = RequestSerializer.serialize(request).toString();
        //Window.alert(requestBuffer);
        socket.send(requestBuffer);
    }

    public void onOpen(ActionHandler onOpenHandler) {
        onOpenHandlers.add(onOpenHandler);
    }

    @Override
    public void onOpen(WebSocket webSocket) {
        console.log(new LogRecord(Level.INFO, "Socket opened"));

        sendRequest(new WSRequest(WSRequest.RequestName.getGameField, 4321));
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
        console.log(new LogRecord(Level.INFO, text));
        Window.alert(text);
        JSONValue wsRequestBuffet = JSONParser.parseLenient(text);
        WSRequest wsRequest = RequestSerializer.deserialize(wsRequestBuffet, WSRequest.class);

        for (TextChangeHandler onChangeHandler : onChangeHandlers) {
            onChangeHandler.onTextChange(text);
        }
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
