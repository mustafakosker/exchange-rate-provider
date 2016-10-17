package com.crossover.currency.service.currency;

import com.crossover.currency.model.ListCurrenciesResponse;
import com.crossover.currency.service.AbstractCurrencyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

/**
 * Created by koskerm on 05/03/2016.
 */
@Service
public class ListCurrenciesServiceImpl extends AbstractCurrencyService implements ListCurrenciesService {
	private static final Logger logger = LoggerFactory.getLogger(ListCurrenciesServiceImpl.class);

	@Autowired
	ListCurrenciesServiceImpl(RestTemplate restTemplate,
							  @Value("${currency.name.list.service}") String serviceMethod) {
		super(restTemplate, serviceMethod);
	}

	@Cacheable("currencies")
	public ListCurrenciesResponse listCurrencies() {
		final String endpointUri = createEndpointUri();
		logger.debug("Sending request for listCurrencies");

		final ListCurrenciesResponse listCurrenciesResponse =
				restTemplate.getForObject(endpointUri, ListCurrenciesResponse.class);

		logger.debug("Received response {}", listCurrenciesResponse);
		return listCurrenciesResponse;
	}
}
