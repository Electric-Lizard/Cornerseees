package eei.cornerseees.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.*;
import eei.cornerseees.client.event.ActionHandler;
import eei.cornerseees.client.event.ItemMoveListener;
import eei.cornerseees.client.event.ItemTakeListener;
import eei.cornerseees.client.service.websocket.CornerseeesEndpoint;
import eei.cornerseees.client.service.websocket.CornerseeesRouter;
import eei.cornerseees.client.service.websocket.MessageHandler;
import eei.cornerseees.client.gamefield.view.GameFieldGrid;
import eei.cornerseees.shared.WSRequest;
import eei.cornerseees.client.gamefield.model.GameFieldModel;
import eei.cornerseees.shared.gamefield.PieceMoving;
import eei.cornerseees.shared.gamefield.PieceTaking;

/**
 * Entry point classes define <code>onModuleLoad()</code>
 */
public class Cornerseees implements EntryPoint {
    protected RootPanel root = RootPanel.get();
    protected CornerseeesEndpoint webSocket = new CornerseeesEndpoint();
    protected CornerseeesRouter router;
    protected GameFieldGrid gameFieldGrid;

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
        MessageHandler gameFieldMessageHandler = new MessageHandler<GameFieldModel>() {
            @Override
            public void handleMessage(String encodedData) {
                GameFieldModel gameField = decodeData(encodedData, GameFieldModel.class);
                resetGameField(gameField);
            }
        };
        router.addMessageHandler(WSRequest.RequestName.gameField, gameFieldMessageHandler);
        router.fetchGameField();
    }

    protected void resetGameField(GameFieldModel gameField) {
        if (gameFieldGrid != null) {
            root.remove(gameFieldGrid);
        }
        gameFieldGrid = new GameFieldGrid(gameField);
        gameFieldGrid.addItemMoveListeners(new ItemMoveListener() {
            @Override
            public void itemMoved(PieceMoving pieceMoving) {
                router.movePiece(pieceMoving);
            }
        });
        gameFieldGrid.addItemTakeListeners(new ItemTakeListener() {
            @Override
            public void itemTaken(PieceTaking pieceTaking) {
                router.pieceTaken(pieceTaking);
            }
        });
        root.add(gameFieldGrid);
    }

    /**
     * Delegates related events
     */
    protected void delegateEvents() {

    }
}
