package com.crossover.currency.service.exchangerate;

import com.crossover.currency.model.service.ExchangeRate;
import com.crossover.currency.model.service.ListExchangeRatesServiceResponse;

/**
 * Created by koskerm on 06/03/2016.
 */
public interface ListLatestRatesService {
	ListExchangeRatesServiceResponse listLatestRates();

	ExchangeRate getExchangeRate(String currencyCode);
}
