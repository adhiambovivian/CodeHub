/* Copyright (C)2021  Vivian */
package com.codeHub.configs;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JMSConfig {

    private static final Log log = LogFactory.getLog(JMSConfig.class);

    //    @Bean
    //    public DefaultMessageListenerContainer jmsListener(ConnectionFactory connectionFactory) {
    //        DefaultMessageListenerContainer jmsListener = new DefaultMessageListenerContainer();
    //        jmsListener.setConnectionFactory(connectionFactory);
    //        jmsListener.setDestinationName("rabbit-trader-channel");
    //        jmsListener.setPubSubDomain(true);
    //
    //        MessageListenerAdapter adapter = new MessageListenerAdapter(new Receiver());
    //        adapter.setDefaultListenerMethod("receive");
    //
    //        jmsListener.setMessageListener(adapter);
    //        return jmsListener;
    //    }

    protected static class Receiver {
        public void receive(String message) {
            log.info("Received " + message);
        }
    }
}
