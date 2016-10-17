package com.crossover.currency.service.exchangerate.conversion;

import com.crossover.currency.model.service.ExchangeRate;
import com.crossover.currency.service.AbstractCurrencyService;
import com.crossover.currency.service.exchangerate.ListLatestRatesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

/**
 * Created by koskerm on 06/03/2016.
 */
@Service
public class CurrencyConversionServiceImpl extends AbstractCurrencyService implements CurrencyConversionService {
	@Autowired
	private ListLatestRatesService listLatestRatesService;

	@Autowired
	public CurrencyConversionServiceImpl(RestTemplate restTemplate) {
		super(restTemplate);
	}

	@Override
	public BigDecimal calculateRates(String sourceCurrencyCode, String targetCurrencyCode, BigDecimal amount) {
		final ExchangeRate sourceExchangeRate = listLatestRatesService.getExchangeRate(sourceCurrencyCode);
		final ExchangeRate targetExchangeRate = listLatestRatesService.getExchangeRate(targetCurrencyCode);

		final BigDecimal targetRate = targetExchangeRate.getRate();
		final BigDecimal sourceRate = sourceExchangeRate.getRate();

		final BigDecimal calculatedRate = sourceRate.divide(targetRate, 5, BigDecimal.ROUND_HALF_UP);

		return calculatedRate.multiply(amount);
	}
}
