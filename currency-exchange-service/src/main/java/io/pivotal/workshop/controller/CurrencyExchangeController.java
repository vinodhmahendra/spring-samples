package io.pivotal.workshop.controller;
import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import io.pivotal.workshop.domain.ExchangeValue;
import io.pivotal.workshop.repository.ExchangeValueRepository;

@RestController
public class CurrencyExchangeController {
	
	@Autowired
	private Environment environment;
	
	@Autowired
	private ExchangeValueRepository repository;
	
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public ExchangeValue retrieveExchangeValue
		(@PathVariable String from, @PathVariable String to){
		
		ExchangeValue exchangeValue = 
				repository.findByFromAndTo(from, to);
		
		//dynamically assign  the value
		exchangeValue.setPort(
				Integer.parseInt(environment.getProperty("local.server.port")));
		
		return exchangeValue;
	}
}