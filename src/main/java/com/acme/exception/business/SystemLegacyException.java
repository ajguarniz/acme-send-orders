package com.acme.exception.business;

/**
 * Excepción para errores de comunicación o respuesta del sistema legacy.
 */
public class SystemLegacyException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = -4899575461914493372L;

	public SystemLegacyException(String message, Throwable cause) {
        super(message, cause);
    }

    public SystemLegacyException(String message) {
        super(message);
    }
}