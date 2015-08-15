package eei.cornerseees.client.serializer;

import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import eei.cornerseees.client.RequestSerializer;
import eei.cornerseees.shared.WSRequest;

import java.util.Set;

/**
 * Created by username on 8/14/15.
 */
public class WSRequestSerializer implements Serializer<WSRequest, JSONObject> {
    @Override
    public JSONObject serialize(WSRequest wsRequest) {
        JSONObject wsRequestJson = new JSONObject();
        wsRequestJson.put("name", new JSONNumber(wsRequest.getName().ordinal()));
        wsRequestJson.put("data", RequestSerializer.serialize((Number) wsRequest.getData()));
        return wsRequestJson;
    }

    @Override
    public WSRequest deserialize(JSONObject wsRequestBuffer) {
        String requestName = wsRequestBuffer.get("name").toString();
        return null;
    }
}
