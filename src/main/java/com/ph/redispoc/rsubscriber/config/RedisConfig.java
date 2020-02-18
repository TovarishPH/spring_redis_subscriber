package com.ph.redispoc.rsubscriber.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.GenericToStringSerializer;

import com.ph.redispoc.rsubscriber.service.MessageSubscriberService;

@Configuration
public class RedisConfig {
	
	@Autowired
	private MessageSubscriberService service;

	@Bean
	public JedisConnectionFactory jedisConnectionFactory() {
		return new JedisConnectionFactory();
	}
	
	@Bean
	public ChannelTopic channelTopic() {
		return new ChannelTopic("teste");
	}
	
	@Bean
	public MessageListenerAdapter messageAdapter() {
		return new MessageListenerAdapter(this.service);
	}
	
	@Bean
	public RedisMessageListenerContainer redisContainer() {
		RedisMessageListenerContainer container = new RedisMessageListenerContainer();
		container.setConnectionFactory(this.jedisConnectionFactory());
		container.addMessageListener(this.messageAdapter(), this.channelTopic());
		return container;
	}
	
}
