package eei.cornerseees.client.service.plain;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface CornerseeesServiceAsync {
    // Sample interface method of remote interface
    void getText(AsyncCallback<String> async);

    void setText(String text, AsyncCallback<Void> async);
}
