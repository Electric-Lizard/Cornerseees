package eei.cornerseees.shared;

import eei.cornerseees.shared.model.beans.SerializableBean;


/**
 * Created by username on 8/11/15.
 */
public interface WSRequest {
    String getMethodName();

    void setMethodName(String methodName);

    SerializableBean[] getMethodParameters();

    void setMethodParameters(SerializableBean[] methodParameters);
}
