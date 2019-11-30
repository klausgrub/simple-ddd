package org.samsun.domain.support;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;

import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * @author sunmin
 * @version 1.0.0
 * @createTime 2019年11月18日
 */
public abstract class AbstractCommandMessageListener implements ChannelAwareMessageListener {

    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        String msg = new String(message.getBody(), StandardCharsets.UTF_8);
        AbstractCommand command = loadFromMessage(msg, message.getMessageProperties().getHeaders());
        command.doEntitySync();
    }

    public abstract AbstractCommand loadFromMessage(String message,  Map<String, Object> headers);
}
