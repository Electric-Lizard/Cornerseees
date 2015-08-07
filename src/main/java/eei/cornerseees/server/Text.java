package eei.cornerseees.server;

import javafx.event.Event;
import javafx.event.EventType;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;

/**
 * Created by username on 8/6/15.
 */
public class Text {
    protected String textData = "NO CHANGED";
    protected Event textChangedEvent = new Event(EventType.ROOT);
    protected List<ActionListener> listeners = new ArrayList<>();

    public void addListener(ActionListener listener) {
        listeners.add(listener);
    }

    public String getTextData() {
        return textData;
    }

    public void setTextData(String textData) {
        this.textData = textData;

        triggerEvent();
    }

    protected void triggerEvent() {
        for (ActionListener listener : listeners) {
            listener.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, getTextData()));
        }
    }

    private static Text ourInstance = new Text();

    public static Text getInstance() {
        return ourInstance;
    }

    private Text() {
    }
}
