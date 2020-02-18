package com.ph.redispoc.rsubscriber.service;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Service;

@Service
public class MessageSubscriberService implements MessageListener {

	@Override
	public void onMessage(Message message, byte[] pattern) {
		System.out.println("Mensagem recebida: " + message.toString());
		System.out.println("Channel: " + message.getChannel().toString());
		System.out.println("Body: " + message.getBody().toString());
	}

	
}
