package org.samsun.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @author sunmin
 * @version 1.0.0
 * @ClassName DomainContext.java
 * @Description TODO
 * @createTime 2019年07月14日
 */
@Component
public class DomainContext {

    private ApplicationContext applicationContext;

    @Autowired
    protected void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }


}
