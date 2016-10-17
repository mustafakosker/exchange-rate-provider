package com.crossover.currency.model.service;

import java.util.List;

/**
 * Created by koskerm on 06/03/2016.
 */
public class ListExchangeRatesServiceResponse {
	private String baseCurrencyCode;

	private List<ExchangeRate> exchangeRateList;

	public ListExchangeRatesServiceResponse() {
	}

	public String getBaseCurrencyCode() {
		return baseCurrencyCode;
	}

	public void setBaseCurrencyCode(String baseCurrencyCode) {
		this.baseCurrencyCode = baseCurrencyCode;
	}

	public List<ExchangeRate> getExchangeRateList() {
		return exchangeRateList;
	}

	public void setExchangeRateList(List<ExchangeRate> exchangeRateList) {
		this.exchangeRateList = exchangeRateList;
	}

	@Override
	public String toString() {
		return "ListExchangeRatesServiceResponse{" +
				"baseCurrencyCode='" + baseCurrencyCode + '\'' +
				", exchangeRateList=" + exchangeRateList +
				'}';
	}
}
