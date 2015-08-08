package eei.cornerseees.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.user.client.rpc.AsyncCallback;
import eei.cornerseees.client.service.TextStream;
import eei.cornerseees.client.service.plain.CornerseeesService;
import eei.cornerseees.client.service.websocket.WebSocketService;
import eei.cornerseees.shared.event.ActionHandler;

/**
 * Entry point classes define <code>onModuleLoad()</code>
 */
public class Cornerseees implements EntryPoint {
    final TextBox textBox = new TextBox();
    RootPanel root = RootPanel.get();
    WebSocketService webSocket = new WebSocketService();
    TextStream textStream;

    /**
     * This is the entry point method.
     */
    public void onModuleLoad() {
        establishConnection(new ActionHandler() {
            @Override
            public void doAction() {
                render();
            }
        });
    }

    protected void establishConnection(ActionHandler onOpen) {
        webSocket.onOpen(onOpen);
        webSocket.establish();
        textStream = webSocket;
    }

    protected void render() {
        final Button button = new Button("Click me");
        final Label label = new Label();

        button.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                String text = textBox.getText();
                CornerseeesService.App.getInstance().setText(text, new AsyncCallback<Void>() {
                    @Override
                    public void onFailure(Throwable caught) {
                        int test = 32;
                    }

                    @Override
                    public void onSuccess(Void result) {
                        int test = 32;
                    }
                });
            }
        });

        root.add(textBox);
        root.add(button);
        root.add(label);
    }
}
