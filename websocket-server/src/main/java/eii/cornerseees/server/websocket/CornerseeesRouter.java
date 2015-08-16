package eii.cornerseees.server.websocket;

import eei.cornerseees.shared.WSRequest;
import eei.cornerseees.shared.gamefield.AvailableStepsCoordinates;
import eei.cornerseees.shared.gamefield.PieceMoving;
import eei.cornerseees.shared.gamefield.PieceTaking;
import eii.cornerseees.server.core.cornerseees.InvalidPieceTakingException;
import eii.cornerseees.server.websocket.service.CornerseeesGameFieldService;
import eii.cornerseees.server.websocket.messageHandler.MessageHandler;

import javax.websocket.Session;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by username on 8/15/15.
 */
public class CornerseeesRouter {
    protected Map<WSRequest.RequestName, MessageHandler> messageHandlers;
    protected CornerseeesGameFieldService gameFieldService = new CornerseeesGameFieldService();

    public void handleRequest(String message, Session session) throws Exception {
        WSRequest.RequestName requestName = RequestSerializer.getRequestName(message);
        MessageHandler messageHandler = messageHandlers.get(requestName);
        if (messageHandler == null) {
            throw new Exception("Request not found");
        }
        messageHandler.handleMessage(message, session);
    }

    protected void assignMessageHandlers() {
        messageHandlers = new HashMap<>();

        messageHandlers.put(WSRequest.RequestName.getGameField, (encodedData, session) -> sendGameField(session));
        messageHandlers.put(WSRequest.RequestName.movePiece, new MovePieceHandler());
        messageHandlers.put(WSRequest.RequestName.pieceTaken, new TakePieceHandler());
    }

    //~ Message Handlers ===============================================================================================

    class MovePieceHandler implements MessageHandler<PieceMoving> {
        @Override
        public void handleMessage(String encodedData, Session session) {
            PieceMoving pieceMoving = decodeData(encodedData, PieceMoving.class);
            gameFieldService.movePiece(pieceMoving);
            sendGameFieldToAll(session);
        }
    }

    class TakePieceHandler implements MessageHandler<PieceTaking> {
        @Override
        public void handleMessage(String encodedData, Session session) {
            try {
                PieceTaking pieceTaking = decodeData(encodedData, PieceTaking.class);
                Set<int[]> availableSteps = gameFieldService.getAvailableSteps(pieceTaking);
                WSRequest request = new WSRequest(WSRequest.RequestName.availableSteps,
                        new AvailableStepsCoordinates(availableSteps, pieceTaking));
                sendRequest(request, session);
            } catch (InvalidPieceTakingException e) {
                e.printStackTrace();
            }
        }
    }

    //~ GameField send =================================================================================================

    protected void sendGameFieldToAll(Session session) {
        sendGameField(session, true);
    }

    protected void sendGameField(Session session) {
        sendGameField(session, false);
    }

    protected void sendGameField(Session session, boolean toAll) {
        WSRequest request = new WSRequest(WSRequest.RequestName.gameField, gameFieldService.getGameField());
        if (toAll) {
            CornerseeesRouter.sendRequestToAll(request, session);
        } else {
            CornerseeesRouter.sendRequest(request, session);
        }
    }

    //~ Request send ===================================================================================================

    static public void sendRequestToAll(WSRequest request, Session session) {
        Set<Session> sessionSet = session.getOpenSessions();
        sendRequest(request, sessionSet.toArray(new Session[sessionSet.size()]));
    }
    static public void sendRequest(WSRequest request, Session... sessions) {
        String encodedRequest = RequestSerializer.serialize(request);
        try {
            sendString(encodedRequest, sessions);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static protected void sendString(String string, Session[] sessions) throws IOException {
        for (Session session : sessions) {
            if (session.isOpen()) {
                session.getAsyncRemote().sendText(string);
            }
        }
    }

    //~ Singleton pattern ==============================================================================================

    private CornerseeesRouter() {
        assignMessageHandlers();
    }
    private static CornerseeesRouter instance = new CornerseeesRouter();
    public static CornerseeesRouter getInstance() {
        return instance;
    }
}
