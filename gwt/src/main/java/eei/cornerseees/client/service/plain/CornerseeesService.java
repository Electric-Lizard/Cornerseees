package eei.cornerseees.client.service.plain;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

//@RemoteServiceRelativePath("CornerseeesService")
public interface CornerseeesService extends RemoteService {
    // Sample interface method of remote interface
    String getText();
    void setText(String text);

    public static class App {
        private static CornerseeesServiceAsync ourInstance = GWT.create(CornerseeesService.class);

        public static synchronized CornerseeesServiceAsync getInstance() {
            return ourInstance;
        }
    }
}
