package com.ph.redispoc.rsubscriber.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

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
	public List<ChannelTopic> channelTopic() {
		//Associando à apenas um canal
		//return new ChannelTopic("teste");
		List<ChannelTopic> channels = new ArrayList<ChannelTopic>();
		channels.add(new ChannelTopic("teste"));
		channels.add(new ChannelTopic("outroTeste"));
		return channels;
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
