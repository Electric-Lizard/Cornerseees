package eei.cornerseees.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import eei.cornerseees.client.CornerseeesService;

public class CornerseeesServiceImpl extends RemoteServiceServlet implements CornerseeesService {
    Text text = Text.getInstance();
    // Implementation of sample interface method
    public String getText() {
        return text.getTextData();
    }

    @Override
    public void setText(String text) {
        this.text.setTextData(text);
    }
}