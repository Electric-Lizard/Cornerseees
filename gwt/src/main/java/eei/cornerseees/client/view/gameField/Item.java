package eei.cornerseees.client.view.gameField;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasMouseDownHandlers;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.InlineHTML;
import com.google.gwt.user.client.ui.Label;

/**
 * Created by username on 8/9/15.
 */
public class Item extends Composite {

    @UiTemplate("template/Item.ui.xml")
    interface ItemUiBinder extends UiBinder<InlineHTML, Item> {}
    private static ItemUiBinder ourUiBinder = GWT.create(ItemUiBinder.class);

    @UiField protected InlineHTML root;

    public Item() {
        initWidget(ourUiBinder.createAndBindUi(this));
        root.setText("\u25C9");
    }

    public Label getRoot() {
        return root;
    }
}