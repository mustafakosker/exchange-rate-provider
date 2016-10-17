package com.crossover.currency.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.crossover.currency.util.ExchangeRateProviderConstants.APP_ID_PARAMETER_KEY;

/**
 * Created by koskerm on 06/03/2016.
 */
public class AbstractCurrencyService {
	protected RestTemplate restTemplate;
	private String serviceMethod;

	@Value("${currency.rates.api.endpoint}")
	private String currencyRatesApiEndpoint;

	@Value("${app.id}")
	private String appId;

	public AbstractCurrencyService(RestTemplate restTemplate) {
		this(restTemplate, null);
	}

	protected AbstractCurrencyService(RestTemplate restTemplate, String serviceMethod) {
		this.restTemplate = restTemplate;
		this.serviceMethod = serviceMethod;
	}

	protected String createEndpointUri() {
		return createEndpointUri(new HashMap<>());
	}

	protected String createEndpointUri(Map<String, List<String>> queryParams) {
		final UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromUriString(currencyRatesApiEndpoint)
				.path(this.serviceMethod);
		final MultiValueMap<String, String> queryParameters = new LinkedMultiValueMap<>(queryParams);
		queryParameters.add(APP_ID_PARAMETER_KEY, appId);

		uriComponentsBuilder.queryParams(queryParameters);

		return uriComponentsBuilder.toUriString();
	}
}
