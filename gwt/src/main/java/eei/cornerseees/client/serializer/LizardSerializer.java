package eei.cornerseees.client.serializer;

import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import eei.cornerseees.shared.model.Lizard;

/**
 * Created by username on 8/14/15.
 */
public class LizardSerializer implements Serializer <Lizard, JSONObject> {

    @Override
    public JSONObject serialize(Lizard lizard) {
        JSONObject lizardBuffer = new JSONObject();
        lizardBuffer.put("length", new JSONNumber(lizard.getLength()));
        lizardBuffer.put("color", new JSONString(lizard.getColor()));
        lizardBuffer.put("name", new JSONString(lizard.getName()));
        return lizardBuffer;
    }

    @Override
    public Lizard deserialize(JSONObject lizardBuffer) {
        Lizard lizard = new Lizard();
        lizard.setName(lizardBuffer.get("name").toString());
        lizard.setColor(lizardBuffer.get("color").toString());
        lizard.setLength(((JSONNumber) lizardBuffer.get("length")).doubleValue());
        return lizard;
    }
}
