package com.example.exceptions;

public class GameSaveException extends Exception{
	
	public GameSaveException() {
		super("No se puede crear un juego");
	}

}
