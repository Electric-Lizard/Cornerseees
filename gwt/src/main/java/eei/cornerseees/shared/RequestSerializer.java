package eei.cornerseees.shared;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.Window;
import com.google.web.bindery.autobean.shared.AutoBean;
import com.google.web.bindery.autobean.shared.AutoBeanCodex;
import com.google.web.bindery.autobean.shared.AutoBeanFactory;
import com.google.web.bindery.autobean.shared.AutoBeanUtils;
import eei.cornerseees.shared.model.Lizard;
import eei.cornerseees.shared.model.beans.LizardBean;
import eei.cornerseees.shared.model.beans.SerializableBean;

/**
 * Created by username on 8/11/15.
 */
public abstract class RequestSerializer {
    interface SerializationFactory extends AutoBeanFactory {
        AutoBean<WSRequest> request();
        AutoBean<WSRequest> request(WSRequest request);
        AutoBean<SerializableBean> lizard();
    }
    static SerializationFactory serializationFactory = GWT.create(SerializationFactory.class);
    static public String serialize(WSRequest data) {
        //Window.alert(data.toString());
        AutoBean<WSRequest> autoBean = AutoBeanUtils.getAutoBean(data);
        return AutoBeanCodex.encode(autoBean).getPayload();
    }

    static public WSRequest deserialize(String serializedRequest) {
        AutoBean<WSRequest> autoBean = AutoBeanCodex.decode(serializationFactory, WSRequest.class, serializedRequest);
        return autoBean.as();
    }
    static public String serializeLizard(Lizard lizard) {
        AutoBean<Lizard> autoBean = AutoBeanUtils.getAutoBean(lizard);
        return AutoBeanCodex.encode(autoBean).getPayload();
    }
    static public Lizard deserializeLizard(String lizard) {
        AutoBean<Lizard> autoBean = AutoBeanCodex.decode(serializationFactory, Lizard.class, lizard);
        return autoBean.as();
    }
}
