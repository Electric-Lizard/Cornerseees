package eei.cornerseees.client.serializer;

import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONValue;
import eei.cornerseees.shared.JSONSerializable;
import eei.cornerseees.shared.gamefield.GameField;

/**
 * Created by username on 8/15/15.
 */
public class GameFieldSerializer implements Serializer<GameField, JSONObject> {
    @Override
    public JSONObject serialize(GameField gameField) {
        return null;
    }

    @Override
    public GameField deserialize(JSONObject gameFieldJson) {
        JSONArray gameFieldRowsJson = (JSONArray) gameFieldJson.get("cells");
        for (int i = 0; i < gameFieldRowsJson.size(); i++) {
            JSONObject gameFieldCellJson = (JSONObject) gameFieldRowsJson.get(i);
        }
        return null;
    }
}
