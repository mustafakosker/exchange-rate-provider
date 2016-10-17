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
@TestPropertySource(properties = {"app.id=TESTAPPID","currency.name.list.service=list"})
public class ListCurrenciesControllerTest extends AbstractTest {

	private String endpointUrl;

	@Before
	public void setUp() throws Exception {
		endpointUrl = "http://apilayer.net/api/list?access_key=TESTAPPID";
	}

	@Test
	public void testListCurrenciesSuccess() throws Exception {
		mockServer.expect(requestTo(endpointUrl))
				.andExpect(method(HttpMethod.GET))
				.andRespond(withSuccess("{\"currencies\":{\"AED\":\"United Arab Emirates Dirham\",\"AFN\":\"Afghan Afghani\"}}",
						MediaType.APPLICATION_JSON));

		mockMvc.perform(get("/listCurrencies")).andExpect(status().isOk());

		mockServer.verify();
	}
}
