package com.acme.model.xml;

/**
 * Clase que contiene el template SOAP para la solicitud de envío de pedido.
 * Se utiliza para mantener el XML separado de la lógica de negocio.
 */
public final class SendOrderRequestXml {

    private SendOrderRequestXml() {}

    public static final String TEMPLATE = """
        <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
                          xmlns:env="http://WSDLs/EnvioPedidos/EnvioPedidosAcme">
            <soapenv:Header/>
            <soapenv:Body>
                <env:EnvioPedidoAcme>
                    <EnvioPedidoRequest>
                        <pedido>%s</pedido>
                        <Cantidad>%s</Cantidad>
                        <EAN>%s</EAN>
                        <Producto>%s</Producto>
                        <Cedula>%s</Cedula>
                        <Direccion>%s</Direccion>
                    </EnvioPedidoRequest>
                </env:EnvioPedidoAcme>
            </soapenv:Body>
        </soapenv:Envelope>
        """;
}