package cn.pickle.rabbit.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Pickle
 * @version V1.0
 * @date 2022/10/4 23:54
 */
@Configuration
public class RabbitMQConfig {

    public final static String EXCHANGE_NAME = "boot_topic_exchange";
    public final static String QUEUE_NAME ="boot_queue";

    //交换机说明
    @Bean(name = "bootExchange")
    public Exchange bootExchange(){
        return ExchangeBuilder.topicExchange(EXCHANGE_NAME).durable(true).build();
    }

    //队列说明
    @Bean(name = "bootQueue")
    public Queue bootQueue(){
        return QueueBuilder.durable(QUEUE_NAME).build();
    }

    //绑定说明
    @Bean
    public Binding bindQueueExchange(@Qualifier("bootQueue") Queue queue,@Qualifier("bootExchange") Exchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with("boot.#").noargs();
    }
}
