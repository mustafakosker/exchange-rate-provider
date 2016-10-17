package com.crossover.controller;

import com.crossover.currency.ExchangeRateProviderApp;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

/**
 * Created by koskerm on 07/03/2016.
 */
public class AbstractTest {
	@Autowired
	private RestTemplate restTemplate;

	protected MockRestServiceServer mockServer;

	protected MockMvc mockMvc;

	@Autowired
	private WebApplicationContext wac;

	@Before
	public void setUpBase() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
		mockServer = MockRestServiceServer.createServer(restTemplate);
	}
}
