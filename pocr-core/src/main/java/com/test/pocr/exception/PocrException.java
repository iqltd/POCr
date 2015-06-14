package com.test.pocr.exception;

public class PocrException extends RuntimeException {

	private static final long serialVersionUID = -6905906336656856924L;

	public PocrException() {
		// TODO Auto-generated constructor stub
	}

	public PocrException(final String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public PocrException(final Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public PocrException(final String message, final Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public PocrException(final String message, final Throwable cause,
			final boolean enableSuppression, final boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
