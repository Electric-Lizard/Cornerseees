package eei.cornerseees.client;

import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONValue;
import eei.cornerseees.client.serializer.*;
import eei.cornerseees.shared.WSRequest;
import eei.cornerseees.client.gamefield.model.GameFieldModel;
import eei.cornerseees.client.gamefield.model.GameFieldCellModel;
import eei.cornerseees.client.gamefield.model.PieceModel;
import eei.cornerseees.shared.gamefield.AvailableStepsCoordinates;
import eei.cornerseees.shared.gamefield.PieceMoving;
import eei.cornerseees.shared.gamefield.PieceTaking;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by username on 8/14/15.
 */
public class RequestSerializer {
    static protected final Map<Class, Serializer> objectSerializers = new HashMap<>();
    static {
        objectSerializers.put(WSRequest.class, new WSRequestSerializer());
        objectSerializers.put(GameFieldModel.class, new GameFieldSerializer());
        objectSerializers.put(GameFieldCellModel.class, new GameFieldCellSerializer());
        objectSerializers.put(PieceModel.class, new PieceSerializer());
        objectSerializers.put(PieceMoving.class, new PieceMovingSerializer());
        objectSerializers.put(PieceTaking.class, new PieceTakingSerializer());
        objectSerializers.put(AvailableStepsCoordinates.class, new AvailableStepsCoordinatesSerializer());
    }

    static public JSONValue serialize(Object data) {
        return data != null ? objectSerializers.get(data.getClass()).serialize(data) : null;
    }

    static public <T> T deserialize(JSONValue data, Class<T> clazz) {
        Serializer serializer = objectSerializers.get(clazz);
        return (T) serializer.deserialize(data);
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