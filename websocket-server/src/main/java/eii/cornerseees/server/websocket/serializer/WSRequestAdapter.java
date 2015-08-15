package eii.cornerseees.server.websocket.serializer;

import com.google.gson.*;
import eei.cornerseees.shared.JSONSerializable;
import eei.cornerseees.shared.WSRequest;
import eii.cornerseees.server.websocket.RequestSerializer;

import java.lang.reflect.Type;
import java.util.Map;
import java.util.Set;

/**
 * Created by username on 8/15/15.
 */
public class WSRequestAdapter implements JsonSerializer<WSRequest>, JsonDeserializer<WSRequest> {
    /**
     * Gson invokes this call-back method during deserialization when it encounters a field of the
     * specified type.
     * <p>In the implementation of this call-back method, you should consider invoking
     * {@link JsonDeserializationContext#deserialize(JsonElement, Type)} method to create objects
     * for any non-trivial field of the returned object. However, you should never invoke it on the
     * the same type passing {@code json} since that will cause an infinite loop (Gson will call your
     * call-back method again).
     *
     * @param json    The Json data being deserialized
     * @param typeOfT The type of the Object to deserialize to
     * @param context
     * @return a deserialized object of the specified type typeOfT which is a subclass of {@code T}
     * @throws JsonParseException if json is not in the expected format of {@code typeofT}
     */
    @Override
    public WSRequest deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        try {
            JsonObject wsRequestBuffer = (JsonObject) json;
            String methodName = wsRequestBuffer.get("name").getAsString();
            JsonObject methodParametersBuffer = wsRequestBuffer.get("methodParameters").getAsJsonObject();
            Set<Map.Entry<String, JsonElement>> entrySet = methodParametersBuffer.entrySet();
           JSONSerializable[] methodParameters = new JSONSerializable[entrySet.size()];

            int i = 0;
            for (Map.Entry<String, JsonElement> entry : entrySet) {
                Class methodClass = Class.forName(entry.getKey());
                methodParameters[i++] = RequestSerializer.deserialize(entry.getValue().toString(), methodClass);
            }
            return new WSRequest(methodName, methodParameters);
        } catch (ClassNotFoundException e) {
            throw new JsonParseException(e);
        }
    }

    /**
     * Gson invokes this call-back method during serialization when it encounters a field of the
     * specified type.
     * <p/>
     * <p>In the implementation of this call-back method, you should consider invoking
     * {@link JsonSerializationContext#serialize(Object, Type)} method to create JsonElements for any
     * non-trivial field of the {@code src} object. However, you should never invoke it on the
     * {@code src} object itself since that will cause an infinite loop (Gson will call your
     * call-back method again).</p>
     *
     * @param src       the object that needs to be converted to Json.
     * @param typeOfSrc the actual type (fully genericized version) of the source object.
     * @param context
     * @return a JsonElement corresponding to the specified object.
     */
    @Override
    public JsonElement serialize(WSRequest src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject wsRequestBuffer = new JsonObject();
        wsRequestBuffer.addProperty("name", src.getName());
        JsonObject methodParametersBuffer = new JsonObject();
        Map<Class, JSONSerializable> methodParameters = src.getMethodParametersMap();
        for (Map.Entry<Class, JSONSerializable> methodEntrySet : methodParameters.entrySet()) {
            methodParametersBuffer.addProperty(
                    methodEntrySet.getKey().getCanonicalName(),
                    RequestSerializer.serialize(methodEntrySet.getValue())
            );
        }
        wsRequestBuffer.add("methodParameters", methodParametersBuffer);
        return wsRequestBuffer;
    }
}
