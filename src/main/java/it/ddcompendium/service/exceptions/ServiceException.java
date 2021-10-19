package it.ddcompendium.service.exceptions;

public class ServiceException extends Exception {
	private static final long serialVersionUID = 7762958786114062771L;

	private int code;

	public ServiceException(int code, String message) {
		super(message);
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

}
