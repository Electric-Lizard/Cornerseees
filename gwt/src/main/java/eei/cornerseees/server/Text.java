package eei.cornerseees.server;

/**
 * Created by username on 8/6/15.
 */
public class Text {
    protected String textData = "NO CHANGED";

    public String getTextData() {
        return textData;
    }

    public void setTextData(String textData) {
        this.textData = textData;
    }

    private static Text ourInstance = new Text();

    public static Text getInstance() {
        return ourInstance;
    }

    private Text() {
    }
}
