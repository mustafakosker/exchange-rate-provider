package com.crossover.currency.service.exchangerate;

import com.crossover.currency.model.ListLatestRatesResponse;
import com.crossover.currency.model.service.ExchangeRate;
import com.crossover.currency.model.service.ListExchangeRatesServiceResponse;
import com.crossover.currency.service.AbstractCurrencyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.crossover.currency.util.ExchangeRateProviderConstants.CURRENCY_CODE_LENGTH;

/**
 * Created by koskerm on 05/03/2016.
 */
@Service
public class ListLatestRatesServiceImpl extends AbstractCurrencyService implements ListLatestRatesService {
	private static final Logger logger = LoggerFactory.getLogger(ListLatestRatesServiceImpl.class);

	@Autowired
	ListLatestRatesServiceImpl(RestTemplate restTemplate,
							   @Value("${latest.currency.rates.service}") String serviceMethod) {
		super(restTemplate, serviceMethod);
	}

	public ListExchangeRatesServiceResponse listLatestRates() {
		String endpointUri = createEndpointUri();
		logger.debug("Sending request to exchange rate server...");

		final ListLatestRatesResponse response = restTemplate.getForObject(endpointUri, ListLatestRatesResponse.class);

		ListExchangeRatesServiceResponse exchangeRatesServiceResponse = new ListExchangeRatesServiceResponse();
		exchangeRatesServiceResponse.setBaseCurrencyCode(response.getSource());

		List<ExchangeRate> exchangeRateList = new ArrayList<>();
		response.getQuotes().entrySet().forEach(e -> exchangeRateList.add(convertEntry(e)));

		exchangeRatesServiceResponse.setExchangeRateList(exchangeRateList);

		logger.debug("Response returned from service: " + exchangeRatesServiceResponse);
		return exchangeRatesServiceResponse;
	}

	public ExchangeRate getExchangeRate(String currencyCode) {
		List<String> currencyCodeList = new ArrayList<>();
		currencyCodeList.add(currencyCode);

		final Map<String, List<String>> queryParameters = new HashMap<>();
		queryParameters.put("currencies", currencyCodeList);

		String endpointUri = createEndpointUri(queryParameters);

		logger.debug("Fetching rates for currency {}", currencyCode);

		ListLatestRatesResponse response = restTemplate.getForObject(endpointUri, ListLatestRatesResponse.class);

		Map.Entry<String, String> currencyRateEntry = response.getQuotes().entrySet().iterator().next();
		ExchangeRate exchangeRate = convertEntry(currencyRateEntry);

		logger.debug("Returning exchange rate {}", exchangeRate);

		return exchangeRate;
	}

	private ExchangeRate convertEntry(Map.Entry<String, String> entry) {
		String currencyCode = entry.getKey().substring(CURRENCY_CODE_LENGTH);
		BigDecimal rate = BigDecimal.ONE.divide(new BigDecimal(entry.getValue()), 6, BigDecimal.ROUND_HALF_UP);

		return new ExchangeRate(currencyCode, rate);
	}
}
