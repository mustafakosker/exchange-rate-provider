package com.crossover.currency.controller;

import com.crossover.currency.model.service.ExchangeRate;
import com.crossover.currency.model.service.ListExchangeRatesServiceResponse;
import com.crossover.currency.service.exchangerate.ListLatestRatesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by koskerm on 05/03/2016.
 */
@CrossOrigin(origins = "*")
@RestController
public class ListExchangeRatesController {
	private static final Logger logger = LoggerFactory.getLogger(ListExchangeRatesController.class);

	@Autowired
	private ListLatestRatesService listLatestRatesService;

	@RequestMapping(value = "/listExchangeRates")
	public ListExchangeRatesServiceResponse listExchangeRates() {
		logger.debug("Calling listExchangeRates service...");

		return listLatestRatesService.listLatestRates();
	}

	@RequestMapping(value = "listExchangeRates/currency/{currencyCode}")
	public ExchangeRate listExchangeRateForCurrency(@PathVariable String currencyCode) {
		logger.debug("Calling getExchangeRate service for currency {}", currencyCode);

		return listLatestRatesService.getExchangeRate(currencyCode);
	}
}
