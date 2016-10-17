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
@TestPropertySource(properties = {"app.id=TESTAPPID","latest.currency.rates.service=live"})
public class ListExchangeRatesControllerTest extends AbstractTest {
	private String endpointUrl;

	@Before
	public void setUp() throws Exception {
		endpointUrl = "http://apilayer.net/api/live?access_key=TESTAPPID";
	}

	@Test
	public void testListExchangeRatesSuccess() throws Exception {
		mockServer.expect(requestTo(endpointUrl))
				.andExpect(method(HttpMethod.GET))
				.andRespond(withSuccess("{\"source\":\"USD\",\"quotes\":{\"USDAED\":3.67295,\"USDAFN\":68.610001," +
						"\"USDALL\":125.909501,\"USDAMD\":490.480011,\"USDANG\":1.790231}}", MediaType.APPLICATION_JSON));

		mockMvc.perform(get("/listExchangeRates")).andExpect(status().isOk());

		mockServer.verify();
	}
}