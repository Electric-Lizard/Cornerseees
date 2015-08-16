package eei.cornerseees.client.serializer;

import com.google.gwt.json.client.JSONObject;
import eei.cornerseees.client.RequestSerializer;
import eei.cornerseees.client.gamefield.model.GameFieldCellModel;
import eei.cornerseees.client.gamefield.model.PieceModel;

/**
 * Created by username on 8/16/15.
 */
public class GameFieldCellSerializer implements Serializer<GameFieldCellModel, JSONObject> {
    @Override
    public JSONObject serialize(GameFieldCellModel data) {
        return null;
    }

    @Override
    public GameFieldCellModel deserialize(JSONObject cellModelJson) {
        JSONObject pieceModelJson = (JSONObject) cellModelJson.get("piece");
        PieceModel pieceModel = pieceModelJson != null ? RequestSerializer.deserialize(pieceModelJson, PieceModel.class) : null;
        return new GameFieldCellModel(pieceModel);
    }
}
