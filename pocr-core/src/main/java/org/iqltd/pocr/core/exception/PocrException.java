package org.iqltd.pocr.core.exception;

public class PocrException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public PocrException(final String message) {
		super(message);
	}

	public PocrException(final String message, final Throwable cause) {
		super(message, cause);
	}

}
