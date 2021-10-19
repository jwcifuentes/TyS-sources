package co.com.mintrabajo.tys.commons.exceptions;

public class WebSecurityException extends NFRException {

	private static final long serialVersionUID = 1L;

	public WebSecurityException() {
		super();
	}

	public WebSecurityException(String message, Throwable cause) {
		super(message, cause);
	}

	public WebSecurityException(String message) {
		super(message);
	}

	public WebSecurityException(Throwable cause) {
		super(cause);
	}

}
