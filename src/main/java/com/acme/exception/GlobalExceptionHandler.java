package com.acme.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import com.acme.exception.business.OrderInvalidException;
import com.acme.exception.business.SystemLegacyException;
import com.acme.util.Constants;

/**
 * Manejo global de excepciones para toda la aplicación.
 * 
 * <p>Centraliza la conversión de excepciones en respuestas HTTP adecuadas
 * y registra los errores de forma estructurada.</p>
 *
 * @author Anderson Guarnizo
 * @version 1.0
 * @since 2026-04-09
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(WebClientResponseException.class)
    public ResponseEntity<String> handleWebClientError(WebClientResponseException ex) {
        log.error(Constants.LOG_ERROR_WEBCLIENT_LEGACY, 
                  ex.getStatusCode(), ex.getResponseBodyAsString());
        return ResponseEntity.status(ex.getStatusCode())
                             .body(Constants.RESPONSE_ERROR_COMUNICACION_EXTERNA);
    }
    
    @ExceptionHandler(OrderInvalidException.class)
    public ResponseEntity<String> handlePedidoInvalido(OrderInvalidException ex) {
        log.warn(Constants.LOG_PEDIDO_INVALIDO, ex.getMessage());
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(SystemLegacyException.class)
    public ResponseEntity<String> handleSistemaLegacy(SystemLegacyException ex) {
        log.error(Constants.LOG_ERROR_SISTEMA_LEGACY, ex.getMessage());
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                             .body(ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidation(MethodArgumentNotValidException ex) {
        log.warn(Constants.LOG_ERROR_VALIDACION, ex.getMessage());
        return ResponseEntity.badRequest().body(Constants.RESPONSE_BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception ex) {
        log.error(Constants.LOG_ERROR_NO_CONTROLADO, ex);
        return ResponseEntity.internalServerError()
                             .body(Constants.RESPONSE_INTERNAL_ERROR);
    }
}
