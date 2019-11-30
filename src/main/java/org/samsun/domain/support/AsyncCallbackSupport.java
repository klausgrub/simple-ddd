package org.samsun.domain.support;

import org.samsun.domain.DomainCallback;
import org.samsun.domain.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author sunmin
 * @version 1.0.0
 * @createTime 2019年11月18日
 */
@Component
public class AsyncCallbackSupport {

    private static AsyncCallback asyncCallback;

    public static Object doInEntity(Entity entity, DomainCallback callback) {
        return asyncCallback.doInEntity(entity,callback);
    }

    @Autowired
    public void setAsyncCallback(AsyncCallback asyncCallback) {
        AsyncCallbackSupport.asyncCallback = asyncCallback;
    }
}
