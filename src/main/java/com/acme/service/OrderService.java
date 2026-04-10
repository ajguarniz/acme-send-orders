package com.acme.service;

import com.acme.dto.request.SendOrderRequestDTO;
import com.acme.dto.response.SendOrderResponseDTO;

/**
 * Interfaz del servicio de pedidos.
 * 
 * <p>Define el contrato público para el procesamiento de pedidos,
 * incluyendo la protección con Retry y Circuit Breaker.</p>
 *
 * @author Anderson Guarnizo
 * @version 1.0
 * @since 2026-04-09
 */
public interface OrderService {

	/**
     * Envía un pedido al sistema legacy.
     * 
     * <p>Este método aplica automáticamente Retry y Circuit Breaker
     * para mayor resiliencia ante fallos del sistema externo.</p>
     *
     * @param request DTO con la información del pedido
     * @return ResponseDTO con el resultado del procesamiento
     */
    SendOrderResponseDTO sendOrder(SendOrderRequestDTO request);
}
