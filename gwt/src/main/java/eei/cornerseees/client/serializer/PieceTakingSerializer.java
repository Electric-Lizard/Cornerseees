package eei.cornerseees.client.serializer;

import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONValue;
import eei.cornerseees.shared.gamefield.PieceTaking;

/**
 * Created by username on 8/16/15.
 */
public class PieceTakingSerializer implements Serializer<PieceTaking, JSONObject> {
    @Override
    public JSONObject serialize(PieceTaking pieceTaking) {
        //Serialize properties
        int[] cellCoordinates = pieceTaking.getCellCoordinates();
        JSONArray cellCoordinatesJson = new JSONArray();
        cellCoordinatesJson.set(0, new JSONNumber((double) cellCoordinates[0]));
        cellCoordinatesJson.set(1, new JSONNumber((double) cellCoordinates[1]));

        //Make JSON Object
        JSONObject pieceMovingJson = new JSONObject();
        pieceMovingJson.put("cellCoordinates", cellCoordinatesJson);

        return pieceMovingJson;
    }

    @Override
    public PieceTaking deserialize(JSONObject data) {
        return null;
    }
}
