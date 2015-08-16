package eei.cornerseees.client;

import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONValue;
import eei.cornerseees.client.serializer.GameFieldSerializer;
import eei.cornerseees.shared.JSONSerializable;
import eei.cornerseees.shared.WSRequest;
import eei.cornerseees.client.serializer.Serializer;
import eei.cornerseees.client.serializer.WSRequestSerializer;
import eei.cornerseees.shared.gamefield.GameField;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by username on 8/14/15.
 */
public class RequestSerializer {
    static protected final Map<Class, Serializer> objectSerializers = new HashMap<>();
    static {
        objectSerializers.put(WSRequest.class, new WSRequestSerializer());
        objectSerializers.put(GameField.class, new GameFieldSerializer());
    }

    static public JSONValue serialize(JSONSerializable data) {
        return objectSerializers.get(data.getClass()).serialize(data);
    }

    static public <T> T deserialize(JSONValue data, Class<T> clazz) {
        Serializer serializer = objectSerializers.get(clazz);
        return (T) serializer.deserialize(data);
    }
    static public JSONNumber serialize(Number number) {
        return new JSONNumber(number.doubleValue());
    }

    static public WSRequest.RequestName getRequestName(String encodedRequest) {
        JSONObject requestJson = (JSONObject) JSONParser.parseStrict(encodedRequest);
        int requestName = (int) ((JSONNumber) requestJson.get("name")).doubleValue();
        return WSRequest.RequestName.values()[requestName];
    }

    static public <T> T getRequestData(String encodedRequest, Class<T> dataType) {
        JSONObject requestJson = (JSONObject) JSONParser.parseStrict(encodedRequest);
        return deserialize(requestJson.get("data"), dataType);
    }
}