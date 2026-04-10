package com.acme.exception.business;

/**
 * Excepción para cuando los datos del pedido son inválidos.
 */
public class OrderInvalidException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6678379978772972026L;

	public OrderInvalidException(String message) {
        super(message);
    }

}
