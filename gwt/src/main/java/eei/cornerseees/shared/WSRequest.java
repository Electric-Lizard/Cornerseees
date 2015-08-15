package eei.cornerseees.shared;

public class WSRequest implements JSONSerializable {
    public enum RequestName {getGameField, gameField}
    protected RequestName name;
    protected JSONSerializable data;

    public WSRequest(RequestName methodName, JSONSerializable messageData) {
        this.name = methodName;
        this.data = messageData;
    }

    public WSRequest() {}

    public RequestName getName() {
        return name;
    }

    public void setName(RequestName name) {
        this.name = name;
    }

    public JSONSerializable getData() {
        return data;
    }

    public void setData(JSONSerializable data) {
        this.data = data;
    }
}
