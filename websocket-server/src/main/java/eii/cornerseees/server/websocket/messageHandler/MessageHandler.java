package eii.cornerseees.server.websocket.messageHandler;

import eii.cornerseees.server.websocket.RequestSerializer;

import javax.websocket.Session;

/**
 * Created by username on 8/15/15.
 */
public interface MessageHandler<DataType> {
    void handleMessage(String encodedData, Session session);

    default DataType decodeData(String encodedData, Class<DataType> clazz) {
        return RequestSerializer.getRequestData(encodedData, clazz);
    }
}
