package com.crossover.currency.controller;

import com.crossover.currency.model.ListCurrenciesResponse;
import com.crossover.currency.service.currency.ListCurrenciesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by koskerm on 07/03/2016.
 */
@CrossOrigin(origins = "*")
@RestController
public class ListCurrenciesController {

	@Autowired
	private ListCurrenciesService listCurrenciesService;

	@RequestMapping("/listCurrencies")
	public ListCurrenciesResponse listCurrencyNames() {
		return listCurrenciesService.listCurrencies();
	}
}
