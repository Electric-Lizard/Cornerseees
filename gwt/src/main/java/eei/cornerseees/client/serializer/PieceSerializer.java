package eei.cornerseees.client.serializer;

import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import eei.cornerseees.client.gamefield.model.PieceModel;

/**
 * Created by username on 8/16/15.
 */
public class PieceSerializer implements Serializer<PieceModel, JSONObject> {
    @Override
    public JSONObject serialize(PieceModel data) {
        return null;
    }

    @Override
    public PieceModel deserialize(JSONObject data) {
        int pieceTeam = (int) ((JSONNumber) data.get("team")).doubleValue();
        return new PieceModel(PieceModel.Team.values()[pieceTeam]);
    }
}
