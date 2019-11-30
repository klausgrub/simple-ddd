package org.samsun.domain.support;

import org.samsun.domain.BizEntity;
import org.samsun.domain.DomainCallback;
import org.samsun.domain.DomainExecutor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class AsyncExecutor<T extends BizEntity> {

    @Async
    public void execute(DomainExecutor executor, DomainCallback<T> callback) {

        executor.execute(callback);
    }
}
