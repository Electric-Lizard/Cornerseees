package eei.cornerseees.shared;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.web.bindery.autobean.shared.AutoBean;
import com.google.web.bindery.autobean.shared.AutoBeanCodex;
import com.google.web.bindery.autobean.shared.AutoBeanFactory;
import com.google.web.bindery.autobean.shared.AutoBeanUtils;
import eei.cornerseees.shared.autoBean.WSRequestAutoBean;

interface ModelsAutoBeanFactory extends AutoBeanFactory {
    AutoBean<WSRequestAutoBean> simpleData();
}
public class AutoBeanSerializer {
    static ModelsAutoBeanFactory factory = GWT.create(ModelsAutoBeanFactory.class);

    static public WSRequestAutoBean makeSimpleData() {
        AutoBean<WSRequestAutoBean> simpleData = factory.simpleData();
        return simpleData.as();
    }

    static public <T> String serializeToJson(T simpleData) {
        AutoBean<T> simpleDataAutoBean = AutoBeanUtils.getAutoBean(simpleData);
        return AutoBeanCodex.encode(simpleDataAutoBean).getPayload();
    }
}
