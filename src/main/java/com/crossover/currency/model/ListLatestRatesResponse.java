package com.crossover.currency.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Map;

/**
 * Created by koskerm on 05/03/2016.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ListLatestRatesResponse {
	private String source;
	private Map<String, String> quotes;

	public ListLatestRatesResponse() {
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public Map<String, String> getQuotes() {
		return quotes;
	}

	public void setQuotes(Map<String, String> quotes) {
		this.quotes = quotes;
	}

	@Override
	public String toString() {
		return "ListLatestRatesResponse{" +
				"source='" + source + '\'' +
				", quotes=" + quotes +
				'}';
	}
}
