package com.acme.config;

import java.time.Duration;

import javax.net.ssl.SSLException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;

import io.netty.channel.ChannelOption;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import reactor.netty.http.client.HttpClient;

/**
 * Configuración centralizada de WebClient.
 * 
 * <p>Proporciona un cliente HTTP reactivo configurado con timeouts y 
 * bypass de SSL (solo para entornos de desarrollo).</p>
 *
 * @author Anderson Guarnizo
 * @version 1.0
 * @since 2026-04-09
 */
@Configuration
public class WebClientConfig {
	
	@Value("${acme.envio.url}")
    private String envioUrl;
	
	/**
     * Crea y configura el bean de WebClient con timeouts y SSL bypass.
     *
     * @return WebClient configurado para llamadas al sistema legacy
     * @throws SSLException si ocurre un error al construir el contexto SSL
     */
	@Bean
    WebClient webClient() throws SSLException {
		
		// Configuración SSL que confía en todos los certificados (solo desarrollo)
        SslContext sslContext = SslContextBuilder
                .forClient()
                .trustManager(InsecureTrustManagerFactory.INSTANCE)   // ← Bypass SSL
                .build();

        HttpClient httpClient = HttpClient.create()
        		.secure(spec -> spec.sslContext(sslContext))
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)   // 5 segundos para conectar
                .responseTimeout(Duration.ofSeconds(10));             // 10 segundos máximo de respuesta

        return WebClient.builder()
                .baseUrl(envioUrl)
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(2 * 1024 * 1024)) // 2MB
                .build();
    }

}
