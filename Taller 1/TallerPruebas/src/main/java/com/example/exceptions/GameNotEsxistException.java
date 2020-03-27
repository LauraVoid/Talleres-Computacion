package com.example.exceptions;

public class GameNotEsxistException extends Exception{
	
	public GameNotEsxistException() {
		super("Game no existe");
	}

}
