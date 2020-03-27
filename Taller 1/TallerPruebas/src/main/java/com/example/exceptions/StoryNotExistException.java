package com.example.exceptions;

public class StoryNotExistException extends Exception {
	
	public StoryNotExistException() {
		super("No existe la historia");
	}

}
