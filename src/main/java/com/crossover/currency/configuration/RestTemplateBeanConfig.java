package com.crossover.currency.configuration;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * Created by koskerm on 05/03/2016.
 */
@Configuration
public class RestTemplateBeanConfig {

	private static final Logger logger = LoggerFactory.getLogger(RestTemplateBeanConfig.class);

	@Value("${max.connections}")
	private int maxConnections;

	@Value("${max.connection.per.route}")
	private int maxConnectionsPerRoute;

	@Value("${http.connection.timeout.in.ms}")
	private int connectionTimeoutInMs;

	@Value("${http.read.timeout.in.ms}")
	private int readTimeoutInMs;

	@Bean
	HttpClient getHttpClient() throws Exception {
		HttpClientBuilder builder = HttpClientBuilder.create()
				.setMaxConnPerRoute(maxConnectionsPerRoute)
				.setMaxConnTotal(maxConnections);

		logger.debug("HttpClient bean initialized with maxConnPerRoute: {} and maxConnTotal: {} ",
				maxConnectionsPerRoute, maxConnections);

		return builder.build();
	}

	@Bean
	HttpComponentsClientHttpRequestFactory getHttpComponentsClientHttpRequestFactory(HttpClient httpClient) {
		HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
		clientHttpRequestFactory.setConnectTimeout(connectionTimeoutInMs);
		clientHttpRequestFactory.setReadTimeout(readTimeoutInMs);

		logger.debug("HttpComponentsClientHttpRequestFactory bean initialized with connectionTimeout: {} and readTimeout: {}",
				connectionTimeoutInMs, readTimeoutInMs);

		return clientHttpRequestFactory;
	}

	@Bean
	RestTemplate getRestTemplate(HttpComponentsClientHttpRequestFactory httpComponentsClientHttpRequestFactory) {
		return new RestTemplate(httpComponentsClientHttpRequestFactory);
	}

}
