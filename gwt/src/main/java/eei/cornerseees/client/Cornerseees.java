package eei.cornerseees.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.user.client.rpc.AsyncCallback;
import eei.cornerseees.client.event.ActionHandler;
import eei.cornerseees.client.event.TextChangeHandler;
import eei.cornerseees.client.service.TextStream;
import eei.cornerseees.client.service.plain.CornerseeesService;
import eei.cornerseees.client.service.websocket.WebSocketService;

/**
 * Entry point classes define <code>onModuleLoad()</code>
 */
public class Cornerseees implements EntryPoint {
    final TextBoxBase textBox = new TextArea();
    RootPanel root = RootPanel.get();
    WebSocketService webSocket = new WebSocketService();
    TextStream textStream;
    boolean isTextBoxFocused = false;

    /**
     * This is the entry point method.
     */
    public void onModuleLoad() {
        establishConnections(new ActionHandler() {
            @Override
            public void doAction() {
                render();
            }
        });
    }

    protected void establishConnections(ActionHandler onSuccess) {
        webSocket.onOpen(onSuccess);
        webSocket.establish();
        textStream = webSocket;
    }

    protected void render() {
        delegateEvents();
        root.add(textBox);
    }

    protected void delegateEvents() {
        textBox.addChangeHandler(new ChangeHandler() {
            @Override
            public void onChange(ChangeEvent event) {
                String text = textBox.getText();
                textStream.save(text);
            }
        });
        textBox.addFocusHandler(new FocusHandler() {
            @Override
            public void onFocus(FocusEvent event) {
                isTextBoxFocused = true;
            }
        });
        textBox.addBlurHandler(new BlurHandler() {
            @Override
            public void onBlur(BlurEvent event) {
                isTextBoxFocused = false;
            }
        });
        root.addDomHandler(new KeyUpHandler() {
            @Override
            public void onKeyUp(KeyUpEvent event) {
                if (isTextBoxFocused) {
                    String text = textBox.getText();
                    textStream.save(text);
                }
            }
        }, KeyUpEvent.getType());

        textStream.onChange(new TextChangeHandler() {
            @Override
            public void onTextChange(String text) {
                updateText(text);
            }
        });
    }

    protected void updateText(String text) {
        textBox.setText(text);
    }
}
