package org.samsun.domain.support;

import org.samsun.domain.DomainCallback;
import org.samsun.domain.DomainExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AsyncExecutorSupport {

    private static AsyncExecutor  asyncExecutor;

    public static void execute(DomainExecutor executor, DomainCallback callback) {
        asyncExecutor.execute(executor, callback);
    }

    @Autowired
    public void setAsyncExecutor(AsyncExecutor asyncExecutor) {
        AsyncExecutorSupport.asyncExecutor = asyncExecutor;
    }
}
