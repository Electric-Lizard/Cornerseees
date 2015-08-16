package eei.cornerseees.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.*;
import eei.cornerseees.client.event.ActionHandler;
import eei.cornerseees.client.service.websocket.CornerseeesEndpoint;
import eei.cornerseees.client.service.websocket.CornerseeesRouter;
import eei.cornerseees.client.service.websocket.MessageHandler;
import eei.cornerseees.client.view.gameField.GameFieldGrid;
import eei.cornerseees.shared.WSRequest;
import eei.cornerseees.shared.gamefield.GameField;

/**
 * Entry point classes define <code>onModuleLoad()</code>
 */
public class Cornerseees implements EntryPoint {
    protected RootPanel root = RootPanel.get();
    protected CornerseeesEndpoint webSocket = new CornerseeesEndpoint();
    protected CornerseeesRouter router;

    /**
     * This is the entry point method.
     */
    public void onModuleLoad() {
        establishConnections(new ActionHandler() {
            @Override
            public void doAction() {
                render();
            }
        });
    }

    /**
     * Establishes connection to server
     *
     * @param onSuccess    success establishing handler
     */
    protected void establishConnections(ActionHandler onSuccess) {
        webSocket.addOnOpenHandler(onSuccess);
        webSocket.establish();
        router = webSocket.getRouter();
    }

    protected void render() {
        delegateEvents();
        MessageHandler gameFieldHandler = new MessageHandler<GameField>() {
            @Override
            public void handleMessage(String encodedData) {
                GameField gameField = decodeData(encodedData, GameField.class);
                GameFieldGrid gameFieldGrid = new GameFieldGrid(gameField);
                root.add(gameFieldGrid);
            }
        };
        router.addMessageHandler(WSRequest.RequestName.gameField, gameFieldHandler);
    }

    /**
     * Delegates related events
     */
    protected void delegateEvents() {

    }
}
