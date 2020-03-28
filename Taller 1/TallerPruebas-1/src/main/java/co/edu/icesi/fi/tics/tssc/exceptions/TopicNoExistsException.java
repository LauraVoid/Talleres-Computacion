package co.edu.icesi.fi.tics.tssc.exceptions;

public class TopicNoExistsException extends Exception{

	public TopicNoExistsException() {
		super("No existe el topic");
	}
}
