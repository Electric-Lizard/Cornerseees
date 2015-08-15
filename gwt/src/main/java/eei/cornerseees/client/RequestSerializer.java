package eei.cornerseees.client;

import com.google.gwt.json.client.JSONValue;
import eei.cornerseees.shared.JSONSerializable;
import eei.cornerseees.shared.WSRequest;
import eei.cornerseees.shared.model.Lizard;
import eei.cornerseees.client.serializer.LizardSerializer;
import eei.cornerseees.client.serializer.Serializer;
import eei.cornerseees.client.serializer.WSRequestSerializer;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by username on 8/14/15.
 */
public class RequestSerializer {
    static protected final Map<Class, Serializer> objectSerializers = new HashMap<>();
    static {
        objectSerializers.put(Lizard.class, new LizardSerializer());
        objectSerializers.put(WSRequest.class, new WSRequestSerializer());
    }

    static public JSONValue serialize(JSONSerializable data) {
        return objectSerializers.get(data.getClass()).serialize(data);
    }

    static public <T extends JSONSerializable> T deserialize(JSONValue data, Class<T> clazz) {
        Serializer serializer = objectSerializers.get(clazz);
        return (T) serializer.deserialize(data);
    }
}