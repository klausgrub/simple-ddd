package org.samsun.domain.support;

import org.samsun.domain.DomainCallback;
import org.samsun.domain.Entity;

/**
 * @author sunmin
 * @version 1.0.0
 * @createTime 2019年11月18日
 */
public abstract class AbstractCommand<T extends Entity> implements DomainCallback<T> {

    public enum Mode {

        SYNC{
            @Override
            Object doEntity(AbstractCommand command) {
                return command.doInEntity(command.t);
            }
        },

        ASYNC {
            @Override
            Object doEntity(AbstractCommand command) {

                return AsyncCallbackSupport.doInEntity(command.t, command);
            }
        },

        MESSAGE {

            @Override
            Object doEntity(AbstractCommand command) {
                //todo send message, 发送entity的Id&class，以及ThreadLocal中的变量
                command.send2Queue();
                return null;
            }
        },
        ;
        abstract Object doEntity(AbstractCommand command) ;
    }

    private T t;

    private Mode mode = Mode.SYNC;

    public AbstractCommand(T t) {
        this.t = t;
    }

    public AbstractCommand(T t, Mode mode) {
        this.t = t;
        this.mode = mode;
    }

    public Object doEntity() {
        return mode.doEntity(this);
    }

    public final Object doEntitySync() {
        return Mode.SYNC.doEntity(this);
    }

    public abstract void  send2Queue();
}
