package org.samsun.domain.support;

import cn.nflow.common.lib.domain.BizEntity;

public class AbstractEvent<T extends BizEntity> {

    private T entity;

    public AbstractEvent(T entity) {
        this.entity = entity;
    }

    public T getEntity() {
        return entity;
    }
}
