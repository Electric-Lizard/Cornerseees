package eii.cornerseees.server.websocket;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import eei.cornerseees.shared.JSONSerializable;
import eei.cornerseees.shared.WSRequest;

/**
 * Created by username on 8/14/15.
 */
public class RequestSerializer {
    static protected Gson gson = new Gson();

    static public String serialize(JSONSerializable data) {
        return gson.toJson(data);
    }

    static public <Type extends JSONSerializable> Type deserialize(String data, Class<Type> clazz) {
        return gson.fromJson(data, clazz);
    }
}

