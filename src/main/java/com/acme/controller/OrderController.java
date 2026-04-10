package com.acme.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.acme.dto.request.SendOrderRequestDTO;
import com.acme.dto.response.SendOrderResponseDTO;
import com.acme.service.OrderService;
import com.acme.util.Constants;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

/**
 * Controlador REST para el envío de pedidos al sistema legacy.
 * 
 * <p>Actúa como punto de entrada principal de la API de pedidos,
 * recibiendo solicitudes JSON y delegando el procesamiento al servicio.</p>
 *
 * @author Anderson Guarnizo
 * @version 1.0
 * @since 2026-04-09
 */
@RestController
@RequestMapping(Constants.API_PATH)
@Tag(name = Constants.TAG_NAME, description = Constants.TAG_DESCRIPTION)
public class OrderController {
	
	private static final Logger log = LoggerFactory.getLogger(OrderController.class);
	
	private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    /**
     * Envía un pedido al sistema legacy.
     *
     * @param request DTO con la información del pedido
     * @return Response con el resultado del procesamiento
     */
    @PostMapping(Constants.API_ENDPOINT_SEND)
    @Operation(summary = Constants.OPERATION_SUMMARY, 
               description = Constants.OPERATION_DESCRIPTION)
    @ApiResponses({
        @ApiResponse(responseCode = Constants.HTTP_STATUS_200, description = Constants.RESPONSE_SUCCESS),
        @ApiResponse(responseCode = Constants.HTTP_STATUS_400, description = Constants.RESPONSE_BAD_REQUEST),
        @ApiResponse(responseCode = Constants.HTTP_STATUS_500, description = Constants.RESPONSE_INTERNAL_ERROR)
    })
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<SendOrderResponseDTO> sendOrder(
            @Valid @RequestBody SendOrderRequestDTO request) {
    	log.info(Constants.LOG_RECIBIDA_SOLICITUD_PEDIDO, request.numPedido());
        SendOrderResponseDTO response = orderService.sendOrder(request);
        log.info(Constants.LOG_PEDIDO_PROCESADO_CORRECTAMENTE, response.codigoEnvio());
        return ResponseEntity.ok(response);
    }

}
