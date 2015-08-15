package eei.cornerseees.client.serializer;

import com.google.gwt.json.client.JSONValue;
import eei.cornerseees.shared.JSONSerializable;

/**
 * Created by username on 8/14/15.
 */
public interface Serializer <Type extends JSONSerializable, JSONType extends JSONValue> {
    JSONType serialize(Type data);
    Type deserialize(JSONType data);
}
