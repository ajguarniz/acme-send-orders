package com.acme.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import com.acme.client.SendOrderClient;
import com.acme.dto.request.SendOrderRequestDTO;
import com.acme.dto.response.SendOrderResponseDTO;

@SpringBootTest
class OrderServiceImplTest {

	@MockitoBean
    private SendOrderClient sendOrderClient;

    @Autowired
    private OrderServiceImpl orderService;

    private SendOrderRequestDTO request;

    @BeforeEach
    void setUp() {
        request = new SendOrderRequestDTO(
                "75630275",
                "1",
                "00110000765191002104587",
                "Armario INVAL",
                "1113987400",
                "CR 72B 45 12 APT 301"
        );
    }

    @Test
    @DisplayName("Debe procesar correctamente un pedido y devolver la respuesta esperada")
    void shouldProcessOrderSuccessfully() {

        String mockXmlResponse = """
            <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:env="http://WSDLs/EnvioPedidos/EnvioPedidosAcme">
               <soapenv:Header/>
               <soapenv:Body>
                  <env:EnvioPedidoAcmeResponse>
                     <EnvioPedidoResponse>
                        <Codigo>80375472</Codigo>
                        <Mensaje>Entregado exitosamente al cliente</Mensaje>
                     </EnvioPedidoResponse>
                  </env:EnvioPedidoAcmeResponse>
               </soapenv:Body>
            </soapenv:Envelope>
            """;

        when(sendOrderClient.sendOrderXml(anyString())).thenReturn(mockXmlResponse);

        SendOrderResponseDTO response = orderService.sendOrder(request);

        assertNotNull(response);
        assertEquals("80375472", response.codigoEnvio());
        assertEquals("Entregado exitosamente al cliente", response.estado());
    }

    /*@Test
    @DisplayName("Debe retornar fallback cuando falla el sistema legacy")
    void shouldReturnFallbackWhenLegacyFails() {

        when(sendOrderClient.sendOrderXml(anyString()))
                .thenThrow(new RuntimeException("Error de conexión"));

        SendOrderResponseDTO response = orderService.sendOrder(request);

        assertNotNull(response);
        assertEquals("00000000", response.codigoEnvio());
        assertEquals("Servicio temporalmente no disponible. Intente más tarde.", response.estado());
    }*/
}