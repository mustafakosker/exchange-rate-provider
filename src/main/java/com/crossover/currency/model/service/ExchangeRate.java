package com.crossover.currency.model.service;

import java.math.BigDecimal;

/**
 * Created by koskerm on 06/03/2016.
 */
public class ExchangeRate {
	private String currencyCode;
	private BigDecimal rate;

	public ExchangeRate(BigDecimal rate) {
		this.rate = rate;
	}

	public ExchangeRate(String currencyCode, BigDecimal rate) {
		this.currencyCode = currencyCode;
		this.rate = rate;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public BigDecimal getRate() {
		return rate;
	}

	@Override
	public String toString() {
		return "ExchangeRate{" +
				"currencyCode='" + currencyCode + '\'' +
				", rate=" + rate +
				'}';
	}
}
