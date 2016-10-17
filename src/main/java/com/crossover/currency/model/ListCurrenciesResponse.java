package com.crossover.currency.model;

import java.util.Map;

/**
 * Created by koskerm on 06/03/2016.
 */
public class ListCurrenciesResponse {
	private Map<String, String> currencies;

	public Map<String, String> getCurrencies() {
		return currencies;
	}

	public void setCurrencies(Map<String, String> currencies) {
		this.currencies = currencies;
	}
}
