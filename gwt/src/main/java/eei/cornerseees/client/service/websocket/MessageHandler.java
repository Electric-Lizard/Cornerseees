package eei.cornerseees.client.service.websocket;

import eei.cornerseees.client.RequestSerializer;

/**
 * Created by username on 8/15/15.
 */
public abstract class MessageHandler<DataType> {
    public abstract void handleMessage(String encodedData);

    public DataType decodeData(String encodedData, Class<DataType> clazz) {
        return RequestSerializer.getRequestData(encodedData, clazz);
    }
}
