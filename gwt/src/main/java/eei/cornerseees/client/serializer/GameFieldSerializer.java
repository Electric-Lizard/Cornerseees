package eei.cornerseees.client.serializer;

import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import eei.cornerseees.client.RequestSerializer;
import eei.cornerseees.client.gamefield.model.GameFieldModel;
import eei.cornerseees.client.gamefield.model.GameFieldCellModel;

/**
 * Created by username on 8/15/15.
 */
public class GameFieldSerializer implements Serializer<GameFieldModel, JSONObject> {
    @Override
    public JSONObject serialize(GameFieldModel gameField) {
        return null;
    }

    @Override
    public GameFieldModel deserialize(JSONObject gameFieldJson) {
        JSONArray gameFieldRowsJson = (JSONArray) gameFieldJson.get("cells");

        GameFieldCellModel[][] cells =
                new GameFieldCellModel[gameFieldRowsJson.size()][((JSONArray) gameFieldRowsJson.get(0)).size()];

        for (int row = 0; row < gameFieldRowsJson.size(); row++) {
            JSONArray gameFieldCellsJson = (JSONArray) gameFieldRowsJson.get(row);
            for (int column = 0; column < gameFieldCellsJson.size(); column++) {
                JSONObject gameFieldCell = (JSONObject) gameFieldCellsJson.get(column);
                GameFieldCellModel cellModel = RequestSerializer.deserialize(gameFieldCell, GameFieldCellModel.class);
                cells[row][column] = cellModel;
            }
        }

        return new GameFieldModel(cells);
    }
}
