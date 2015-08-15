package eii.cornerseees.server.websocket;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import eei.cornerseees.shared.JSONSerializable;
import eei.cornerseees.shared.WSRequest;

/**
 * Created by username on 8/14/15.
 */
public class RequestSerializer {
    static protected Gson gson = new Gson();
    static protected JsonParser parser = new JsonParser();

    static public String serialize(Object data) {
        return gson.toJson(data);
    }

    static public <Type> Type deserialize(String data, Class<Type> clazz) {
        return gson.fromJson(data, clazz);
    }

    static public WSRequest.RequestName getRequestName(String encodedRequest) {
        JsonObject requestJson = parser.parse(encodedRequest).getAsJsonObject();
        int requestName = requestJson.get("name").getAsInt();
        return WSRequest.RequestName.values()[requestName];
    }

    static public <T> T getRequestData(String encodedRequest, Class<T> dataType) {
        JsonObject requestJson = parser.parse(encodedRequest).getAsJsonObject();
        return deserialize(requestJson.get("data").toString(), dataType);
    }
}

