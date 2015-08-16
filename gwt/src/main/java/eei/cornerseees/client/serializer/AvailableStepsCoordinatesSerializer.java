package eei.cornerseees.client.serializer;

import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import eei.cornerseees.client.RequestSerializer;
import eei.cornerseees.client.gamefield.model.GameFieldCellModel;
import eei.cornerseees.shared.gamefield.AvailableStepsCoordinates;
import eei.cornerseees.shared.gamefield.PieceTaking;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by username on 8/17/15.
 */
public class AvailableStepsCoordinatesSerializer implements Serializer<AvailableStepsCoordinates, JSONObject> {
    @Override
    public JSONObject serialize(AvailableStepsCoordinates data) {
        return null;
    }

    @Override
    public AvailableStepsCoordinates deserialize(JSONObject availableSteps) {
        JSONArray availableStepsCoordinatesJson = (JSONArray) availableSteps.get("coordinates");

        Set<int[]> coordinates = new HashSet<>();

        for (int i = 0; i < availableStepsCoordinatesJson.size(); i++) {
            JSONArray coordinateJson = (JSONArray) availableStepsCoordinatesJson.get(i);
            coordinates.add(new int[] {
                    (int) coordinateJson.get(0).isNumber().doubleValue(),
                    (int) coordinateJson.get(1).isNumber().doubleValue()
            });
        }

        PieceTaking pieceTaking =
                RequestSerializer.deserialize(availableSteps.get("pieceTaking"), PieceTaking.class);

        return new AvailableStepsCoordinates(coordinates, pieceTaking);
    }
}
