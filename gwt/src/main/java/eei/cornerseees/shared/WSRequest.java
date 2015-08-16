package eei.cornerseees.shared;

public class WSRequest implements JSONSerializable {
    public enum RequestName {getGameField, gameField, movePiece, pieceTaken, availableSteps}
    protected RequestName name;
    protected Object data;

    public WSRequest(RequestName methodName, Object messageData) {
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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
