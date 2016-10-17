package com.crossover.currency.controller;

import com.crossover.currency.model.service.ExchangeRate;
import com.crossover.currency.service.exchangerate.conversion.CurrencyConversionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * Created by koskerm on 05/03/2016.
 */
@CrossOrigin(origins = "*")
@RestController
public class CurrencyConversionController {
	private static final Logger logger = LoggerFactory.getLogger(CurrencyConversionController.class);

	@Autowired
	private CurrencyConversionService currencyConversionService;

	@RequestMapping("/calculateExchangeRate/sourceCurrency/{sourceCurrency}/targetCurrency/{targetCurrency}/amount/{amount}")
	public ExchangeRate calculateCurrency(@PathVariable String sourceCurrency,
									@PathVariable String targetCurrency,
									@PathVariable String amount) {
		logger.debug("Calling currencyConversionService with parameters {} {} {}", sourceCurrency, targetCurrency, amount);

		BigDecimal result = currencyConversionService.calculateRates(sourceCurrency, targetCurrency, new BigDecimal(amount));

		return new ExchangeRate(result);
	}
}
