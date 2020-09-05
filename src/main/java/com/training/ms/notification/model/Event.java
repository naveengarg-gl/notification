package com.training.ms.notification.model;

import java.util.Map;

public class Event {
	
	int messageId;
	String message;
	Map<Object,Object> messagePayload;
	public int getMessageId() {
		return messageId;
	}
	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Map<Object, Object> getMessagePayload() {
		return messagePayload;
	}
	public void setMessagePayload(Map<Object, Object> messagePayload) {
		this.messagePayload = messagePayload;
	}
	public Event(int messageId, String message, Map<Object, Object> messagePayload) {
		super();
		this.messageId = messageId;
		this.message = message;
		this.messagePayload = messagePayload;
	}
	public Event() {
		super();
		// TODO Auto-generated constructor stub
	}
}
