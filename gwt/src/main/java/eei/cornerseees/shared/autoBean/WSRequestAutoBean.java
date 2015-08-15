package eei.cornerseees.shared.autoBean;

import eei.cornerseees.shared.WSRequest;

/**
 * Created by username on 8/15/15.
 */
public interface WSRequestAutoBean<T> {
    WSRequest.RequestName getName();
    void setName(WSRequest.RequestName name);
    T getData();
    void setData(T data);
}
