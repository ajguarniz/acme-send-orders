package com.acme.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.acme.client.SendOrderClient;
import com.acme.dto.request.SendOrderRequestDTO;
import com.acme.dto.response.SendOrderResponseDTO;
import com.acme.exception.business.OrderInvalidException;
import com.acme.model.xml.SendOrderRequestXml;
import com.acme.service.OrderService;
import com.acme.util.Constants;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;

/**
 * Implementación del servicio de pedidos.
 * 
 * <p>Contiene la lógica de negocio para transformar y enviar pedidos
 * al sistema legacy, con protección de Retry y Circuit Breaker.</p>
 *
 * @author Anderson Guarnizo
 * @version 1.0
 * @since 2026-04-09
 */
@Service
public class OrderServiceImpl implements OrderService {

    private static final Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);

    private final SendOrderClient sendOrderClient;

    public OrderServiceImpl(SendOrderClient sendOrderClient) {
        this.sendOrderClient = sendOrderClient;
    }

    /**
     * Envía el pedido con protección de Retry + Circuit Breaker
     */
    @Override
    @Retry(name = Constants.RETRY_NAME, fallbackMethod = Constants.FALLBACK_METHOD)
    @CircuitBreaker(name = Constants.CIRCUIT_BREAKER_NAME, fallbackMethod = Constants.FALLBACK_METHOD)
    public SendOrderResponseDTO sendOrder(SendOrderRequestDTO request) {
    	
    	validarPedido(request);   // Validación de negocio
        log.info(Constants.LOG_PEDIDO_RECIBIDO, request.numPedido());

        String xmlRequest = buildXmlRequest(request);
        log.debug(Constants.LOG_XML_ENVIADO_LEGACY, xmlRequest);

        String xmlResponse = sendOrderClient.sendOrderXml(xmlRequest);
        log.debug(Constants.LOG_XML_RECIBIDO_LEGACY, xmlResponse);

        return parseXmlToJson(xmlResponse);
    }
    
    /**
     * Validación de negocio antes de procesar el pedido
     */
    private void validarPedido(SendOrderRequestDTO request) {
        if (request == null || 
            request.numPedido() == null || request.numPedido().isBlank()) {
            throw new OrderInvalidException(Constants.ERROR_PEDIDO_OBLIGATORIO);
        }
    }

    /**
     * Fallback común para Retry y Circuit Breaker
     */
    public SendOrderResponseDTO fallback(SendOrderRequestDTO request, Throwable t) {
        log.warn(Constants.LOG_OPERACION_FALLIDA, 
                 request.numPedido(), t.getMessage());

        return new SendOrderResponseDTO(
        		Constants.FALLBACK_CODIGO,
                Constants.FALLBACK_MENSAJE
        );
    }

    private String buildXmlRequest(SendOrderRequestDTO request) {
        return String.format(SendOrderRequestXml.TEMPLATE,
                request.numPedido(),
                request.cantidadPedido(),
                request.codigoEAN(),
                request.nombreProducto(),
                request.numDocumento(),
                request.direccion());
    }

    private SendOrderResponseDTO parseXmlToJson(String xml) {
        return new SendOrderResponseDTO(
                extractTag(xml, Constants.XML_TAG_CODIGO),
                extractTag(xml, Constants.XML_TAG_MENSAJE)
        );
    }

    private String extractTag(String xml, String tagName) {
        String openTag = "<" + tagName + ">";
        String closeTag = "</" + tagName + ">";
        int start = xml.indexOf(openTag) + openTag.length();
        int end = xml.indexOf(closeTag, start);
        return (start > openTag.length() && end > start)
                ? xml.substring(start, end).trim()
                : "";
    }
}