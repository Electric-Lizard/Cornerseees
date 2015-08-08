package eei.cornerseees.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Entry point classes define <code>onModuleLoad()</code>
 */
public class Cornerseees implements EntryPoint {
    final TextBox textBox = new TextBox();
    RootPanel root = RootPanel.get();

    /**
     * This is the entry point method.
     */
    public void onModuleLoad() {
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

    protected void fetchText() {
        CornerseeesService.App.getInstance().getText(new AsyncCallback<String>() {
            @Override
            public void onFailure(Throwable caught) {
                caught.printStackTrace();
            }

            @Override
            public void onSuccess(String result) {
                textBox.setText(result);
            }
        });
    }
}
