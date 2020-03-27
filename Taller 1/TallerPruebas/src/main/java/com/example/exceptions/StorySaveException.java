package com.example.exceptions;

public class StorySaveException extends Exception {
	
	public StorySaveException() {
		super("No es posible guardar el story");
	}

}
