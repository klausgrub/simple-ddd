package org.samsun.repository;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Repository;

/**
 * @author sunmin
 * @version 1.0.0
 * @ClassName RepositoryListener.java
 * @Description TODO
 * @createTime 2019年06月07日
 */
public class RepositoryListener implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        ApplicationContext ctx = contextRefreshedEvent.getApplicationContext();

        ctx.getBeansWithAnnotation(Repository.class);
    }
}
