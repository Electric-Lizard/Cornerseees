package eei.cornerseees.client.serializer;

import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONValue;
import eei.cornerseees.shared.gamefield.PieceMoving;

/**
 * Created by username on 8/16/15.
 */
public class PieceMovingSerializer implements Serializer<PieceMoving, JSONObject> {
    @Override
    public JSONObject serialize(PieceMoving pieceMoving) {
        //Serialize properties
        int[] givingCellCoordinates = pieceMoving.getGivingCellCoordinates();
        JSONArray givingCellCoordinatesJson = new JSONArray();
        givingCellCoordinatesJson.set(0, new JSONNumber((double) givingCellCoordinates[0]));
        givingCellCoordinatesJson.set(1, new JSONNumber((double) givingCellCoordinates[1]));

        int[] acceptingCellCoordinates = pieceMoving.getAcceptingCellCoordinates();
        JSONArray acceptingCellCoordinatesJson = new JSONArray();
        acceptingCellCoordinatesJson.set(0, new JSONNumber((double) acceptingCellCoordinates[0]));
        acceptingCellCoordinatesJson.set(1, new JSONNumber((double) acceptingCellCoordinates[1]));

        //Make JSON Object
        JSONObject pieceMovingJson = new JSONObject();
        pieceMovingJson.put("givingCellCoordinates", givingCellCoordinatesJson);
        pieceMovingJson.put("acceptingCellCoordinates", acceptingCellCoordinatesJson);

        return pieceMovingJson;
    }

    @Override
    public PieceMoving deserialize(JSONObject data) {
        return null;
    }
}
