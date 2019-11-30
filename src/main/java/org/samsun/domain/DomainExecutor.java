package org.samsun.domain;

/**
 * @author sunmin
 * @version 1.0.0
 * @ClassName DomainExecutor.java
 * @Description TODO
 * @createTime 2019年07月14日
 */
public interface DomainExecutor<T extends DomainCallback> {

    Object execute(T domainCallback, Object... args);
}
