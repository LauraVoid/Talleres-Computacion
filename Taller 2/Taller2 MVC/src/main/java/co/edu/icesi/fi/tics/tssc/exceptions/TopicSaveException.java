package co.edu.icesi.fi.tics.tssc.exceptions;

public class TopicSaveException extends Exception {
	
	public TopicSaveException(int value) {		
		
		super(messageException(value));
	}
	
	public static String messageException(int value) {
		String message="";
		switch(value) {
		case 1:
			message="El número de grupos es invalido";
			break;
			
		case 2:
			message="El número de Sprints es invalido";
			break;
			
		case 3:
			message="El tema no puede ser nulo";
			break;
		}
		
		return message;
	}

}
