package com.example.exceptions;

public class TopicNoExistsException extends Exception{

	public TopicNoExistsException() {
		super("No existe el topic");
	}
}
