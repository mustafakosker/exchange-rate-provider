package com.crossover.controller;

import com.crossover.currency.ExchangeRateProviderApp;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by koskerm on 07/03/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {ExchangeRateProviderApp.class})
@WebAppConfiguration
@TestPropertySource(properties = {"app.id=TESTAPPID","currency.name.list.service=live"})
public class CurrencyConversionControllerTest extends AbstractTest {

	private static final String requestUri = "/calculateExchangeRate/sourceCurrency/%s/targetCurrency/%s/amount/%s";

	@Test
	public void testListExchangeRatesSuccess() throws Exception {
		String fromCurrency = "EUR";
		String toCurrency = "TRY";
		String amount = "10";

		String endpointUrlFormat = "http://apilayer.net/api/live?currencies=%s&access_key=TESTAPPID";
		String endpointUrlFormatForSourceCurrency = String.format(endpointUrlFormat, fromCurrency);
		String endpointUrlFormatForTargetCurrency = String.format(endpointUrlFormat, toCurrency);

		String formattedEndpointUri = String.format(requestUri, fromCurrency, toCurrency, amount);

		mockServer.expect(requestTo(endpointUrlFormatForSourceCurrency))
				.andExpect(method(HttpMethod.GET))
				.andRespond(withSuccess("{\"source\":\"USD\",\"quotes\":{\"USDTRY\":2.912901,\"USDEUR\":0.908348}}",
						MediaType.APPLICATION_JSON));

		mockServer.expect(requestTo(endpointUrlFormatForTargetCurrency))
				.andExpect(method(HttpMethod.GET))
				.andRespond(withSuccess("{\"source\":\"USD\",\"quotes\":{\"USDTRY\":2.912901,\"USDEUR\":0.908348}}",
						MediaType.APPLICATION_JSON));

		mockMvc.perform(get(formattedEndpointUri)).andExpect(status().isOk());

		mockServer.verify();
	}


}
