package com.acme.util;

public final class Constants {

    private Constants() {
        // Evita instanciación
    }

    // ==================== Namespaces XML ====================
    public static final String SOAP_ENV_NAMESPACE = "http://schemas.xmlsoap.org/soap/envelope/";
    public static final String ACME_NAMESPACE = "http://WSDLs/EnvioPedidos/EnvioPedidosAcme";

    // ==================== Mensajes de Log (Service) ====================
    public static final String LOG_PEDIDO_RECIBIDO = "Procesando pedido - numPedido: {}";
    public static final String LOG_XML_ENVIADO_LEGACY = "XML enviado al sistema legacy:\n{}";
    public static final String LOG_XML_RECIBIDO_LEGACY = "XML recibido del sistema legacy:\n{}";
    public static final String LOG_OPERACION_FALLIDA = "Operación fallida para pedido {} - Motivo: {}";

    // ==================== Mensajes de Log (Controller) ====================
    public static final String LOG_RECIBIDA_SOLICITUD_PEDIDO = "Recibida solicitud de envío de pedido - numPedido: {}";
    public static final String LOG_PEDIDO_PROCESADO_CORRECTAMENTE = "Pedido procesado correctamente - codigoEnvio: {}";

    // ==================== Controller Paths ====================
    public static final String API_PATH = "/api/orders";
    public static final String API_ENDPOINT_SEND = "/send";

    // ==================== Swagger / OpenAPI ====================
    public static final String TAG_NAME = "Pedidos ACME";
    public static final String TAG_DESCRIPTION = "API de abastecimiento - JSON ↔ XML";
    public static final String OPERATION_SUMMARY = "Enviar pedido a sistema de logística";
    public static final String OPERATION_DESCRIPTION = "Recibe un pedido en formato JSON, lo transforma a XML y lo envía al sistema legacy";

    // ==================== HTTP Status Codes (para Swagger) ====================
    public static final String HTTP_STATUS_200 = "200";
    public static final String HTTP_STATUS_400 = "400";
    public static final String HTTP_STATUS_500 = "500";
    
    // ==================== Mensajes de Respuesta ====================
    public static final String RESPONSE_SUCCESS = "Pedido procesado correctamente";
    public static final String RESPONSE_BAD_REQUEST = "Datos de entrada inválidos";
    public static final String RESPONSE_INTERNAL_ERROR = "Error interno del servidor";
    
    // ==================== Mensajes de Validación y Fallback ====================
    public static final String ERROR_PEDIDO_OBLIGATORIO = "El número de pedido es obligatorio";
    public static final String FALLBACK_CODIGO = "00000000";
    public static final String FALLBACK_MENSAJE = "Servicio temporalmente no disponible. Intente más tarde.";
    
    // ==================== Resilience4j ====================
    public static final String RETRY_NAME = "sendOrderRetry";
    public static final String CIRCUIT_BREAKER_NAME = "sendOrder";
    public static final String FALLBACK_METHOD = "fallback";

    // ==================== XML Tags ====================
    public static final String XML_TAG_CODIGO = "Codigo";
    public static final String XML_TAG_MENSAJE = "Mensaje";
    
    // ==================== GlobalExceptionHandler Messages ====================
    public static final String LOG_ERROR_WEBCLIENT_LEGACY = "Error al llamar al sistema legacy. Status: {} - Body: {}";
    public static final String RESPONSE_ERROR_COMUNICACION_EXTERNA = "Error en comunicación con sistema externo";
    public static final String LOG_PEDIDO_INVALIDO = "Pedido inválido: {}";
    public static final String LOG_ERROR_SISTEMA_LEGACY = "Error en sistema legacy: {}";
    public static final String LOG_ERROR_VALIDACION = "Error de validación: {}";
    public static final String LOG_ERROR_NO_CONTROLADO = "Error no controlado";
}