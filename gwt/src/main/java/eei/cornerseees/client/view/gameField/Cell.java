package eei.cornerseees.client.view.gameField;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.*;

/**
 * Created by username on 8/9/15.
 */
public class Cell extends Composite {
    @UiTemplate("template/Cell.ui.xml")
    interface CellUiBinder extends UiBinder<SimplePanel, Cell> {}
    private static CellUiBinder ourUiBinder = GWT.create(CellUiBinder.class);

    @UiField SimplePanel root;
    Widget item;

    public Cell() {
        initWidget(ourUiBinder.createAndBindUi(this));
    }

    public void setItem(Widget item) {
        if (item != null) {
            root.remove(item);
        }

        root.add(item);
        this.item = item;
    }
}