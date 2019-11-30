package org.samsun.domain.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class EventPublisher {

    private static ApplicationContext applicationContext;

    @Autowired
    protected void setApplicationContext(ApplicationContext applicationContext) {
        EventPublisher.applicationContext = applicationContext;
    }

    public static void publish(AbstractEvent event) {
        applicationContext.publishEvent(event);
    }
}
