package org.samsun.domain.support;

import org.samsun.domain.DomainCallback;
import org.samsun.domain.Entity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;


/**
 * @author sunmin
 * @version 1.0.0
 * @createTime 2019年11月18日
 */
@Component
public class AsyncCallback<T extends Entity> {

    @Async
    public Object doInEntity(T entity, DomainCallback<T> callback) {
        return callback.doInEntity(entity);
    }
}
