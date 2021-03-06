package com.ph.redispoc.rsubscriber.service;

import java.time.LocalDateTime;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Service;

@Service
public class MessageSubscriberService implements MessageListener {

	@Override
	public void onMessage(Message message, byte[] pattern) {
		System.out.println("Mensagem recebida: " + message.toString() + " - " + LocalDateTime.now());
	}
	
}
