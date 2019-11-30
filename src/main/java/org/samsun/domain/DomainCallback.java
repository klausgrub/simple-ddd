package org.samsun.domain;

/**
 * @author sunmin
 * @version 1.0.0
 * @ClassName DomainCallback.java
 * @Description TODO
 * @createTime 2019年07月14日
 */
public interface DomainCallback<T extends Entity> {

    Object doInEntity(T entity);
}
