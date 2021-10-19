package co.com.mintrabajo.tys.commons.exceptions;

public class NFRException extends Exception {

	private static final long serialVersionUID = 1L;

	public NFRException() {
		super();
	}

	public NFRException(String message, Throwable cause) {
		super(message, cause);
	}

	public NFRException(String message) {
		super(message);
	}

	public NFRException(Throwable cause) {
		super(cause);
	}

}
