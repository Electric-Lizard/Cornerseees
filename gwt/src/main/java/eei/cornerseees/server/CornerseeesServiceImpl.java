package eei.cornerseees.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import eei.cornerseees.client.service.plain.CornerseeesService;

public class CornerseeesServiceImpl extends RemoteServiceServlet implements CornerseeesService {
    String text = "Text";
    // Implementation of sample interface method
    public String getText() {
        return text;
    }

    @Override
    public void setText(String text) {
        this.text = text;
    }
}