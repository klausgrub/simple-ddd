package org.samsun.util;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;

/**
 * @author sunmin
 * @version 1.0.0
 * @createTime 2019年10月21日
 */
public class ContextAwareExecutor extends ThreadPoolTaskExecutor {

    @Override
    public void execute(Runnable command) {
        super.execute(decorateContextAware(command));
    }

    @Override
    public void execute(Runnable task, long startTimeout) {
        super.execute(decorateContextAware(task), startTimeout);
    }

    @Override
    public Future<?> submit(Runnable task) {
        return super.submit(decorateContextAware(task));
    }

    @Override
    public <T> Future<T> submit(Callable<T> task) {
        return super.submit(decorateContextAware(task));
    }

    @Override
    public ListenableFuture<?> submitListenable(Runnable task) {
        return super.submitListenable(decorateContextAware(task));
    }

    @Override
    public <T> ListenableFuture<T> submitListenable(Callable<T> task) {
        return super.submitListenable(decorateContextAware(task));
    }


    private Runnable decorateContextAware(Runnable command) {
        final String user = ThreadLocalContextUtils.getUser();
        return () -> {
            // add codes with decorate model
            before(user);
            command.run();
            after(user);
        };
    }

    private <T> Callable<T> decorateContextAware(Callable<T> command) {
        final String user = ThreadLocalContextUtils.getUser();

        return () -> {

            // add codes with decorate model
            before(user);
            T ret = command.call();
            after(user);

            return ret;
        };
    }

    private void before(String user) {
        ThreadLocalContextUtils.removeUser();
        if (user != null) {
            // set the desired context that was present at point of calling execute
            ThreadLocalContextUtils.setUser(user);
        }
    }

    private void after(String user) {
        ThreadLocalContextUtils.removeUser();
        if (user != null) {
            // reset the context
            ThreadLocalContextUtils.setUser(user);
        }
    }
}
