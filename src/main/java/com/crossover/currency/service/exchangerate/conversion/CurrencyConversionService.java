package com.crossover.currency.service.exchangerate.conversion;

import java.math.BigDecimal;

/**
 * Created by koskerm on 06/03/2016.
 */
public interface CurrencyConversionService {
	BigDecimal calculateRates(String sourceCurrencyCode, String targetCurrencyCode, BigDecimal amount);
}
