package eei.cornerseees.client;

import com.allen_sauer.gwt.dnd.client.PickupDragController;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.user.client.ui.*;
import eei.cornerseees.client.event.ActionHandler;
import eei.cornerseees.client.event.TextChangeHandler;
import eei.cornerseees.client.service.TextStream;
import eei.cornerseees.client.service.websocket.WebSocketService;
import eei.cornerseees.client.view.gameField.GameFieldGrid;

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

    /**
     * Establishes connection to server
     *
     * @param onSuccess    success establishing handler
     */
    protected void establishConnections(ActionHandler onSuccess) {
        webSocket.onOpen(onSuccess);
        webSocket.establish();
        textStream = webSocket;
    }

    protected void render() {
        delegateEvents();
        //root.add(textBox);

        /***/
        GameFieldGrid gameFieldGrid = new GameFieldGrid(10, 10);
        root.add(gameFieldGrid);
    }

    /**
     * Delegates related events
     */
    protected void delegateEvents() {
        //on change
        textBox.addChangeHandler(new ChangeHandler() {
            @Override
            public void onChange(ChangeEvent event) {
                String text = textBox.getText();
                textStream.save(text);
            }
        });
        //Focus hack
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
        //on keyup
        root.addDomHandler(new KeyUpHandler() {
            @Override
            public void onKeyUp(KeyUpEvent event) {
                if (isTextBoxFocused) {
                    String text = textBox.getText();
                    textStream.save(text);
                }
            }
        }, KeyUpEvent.getType());

        /*make server sync*/
        textStream.onChange(new TextChangeHandler() {
            @Override
            public void onTextChange(String text) {
                updateText(text);
            }
        });
    }

    /**
     * Update text on TextBox
     *
     * @param text text to update
     */
    protected void updateText(String text) {
        textBox.setText(text);
    }
}
