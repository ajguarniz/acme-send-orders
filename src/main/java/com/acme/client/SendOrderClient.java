package com.acme.client;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * Cliente HTTP para comunicarse con el sistema legacy mediante SOAP/XML.
 * 
 * <p>Utiliza WebClient para realizar llamadas reactivas y no bloqueantes.</p>
 *
 * @author Anderson Guarnizo
 * @version 1.0
 * @since 2026-04-09
 */
@Component
public class SendOrderClient {

	private final WebClient webClient;

	//Se inyecta el WebClient ya configurado
    public SendOrderClient(WebClient webClient) {
        this.webClient = webClient;
    }
	
    /**
     * Envía el XML del pedido al sistema legacy.
     *
     * @param xmlRequest XML en formato SOAP
     * @return Respuesta XML del sistema legacy
     */
	public String sendOrderXml(String xmlRequest) {
        return webClient.post()
                .contentType(org.springframework.http.MediaType.TEXT_XML)
                .accept(org.springframework.http.MediaType.TEXT_XML)
                .bodyValue(xmlRequest)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}
