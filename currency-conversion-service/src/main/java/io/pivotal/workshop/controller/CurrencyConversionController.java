package io.pivotal.workshop.controller;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.pivotal.workshop.model.CurrencyConversion;
import io.pivotal.workshop.proxy.CurrencyExchangeServiceProxy;

@RestController
public class CurrencyConversionController {
	
	@Autowired
	private CurrencyExchangeServiceProxy exchangeServiceProxy;
	
	
	@GetMapping("/currency-converter-feign/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversion convertCurrency(@PathVariable String from,
			@PathVariable String to,
			@PathVariable BigDecimal quantity
			){
		
		CurrencyConversion response = exchangeServiceProxy.retrieveExchangeValue(from, to);
		
		return new CurrencyConversion(response.getId(),from,to,response.getConversionMultiple(),
				quantity,quantity.multiply(response.getConversionMultiple()),response.getPort()); 
	}

	
//	@GetMapping("/currency-converter/from/{from}/to/{to}/quantity/{quantity}")
//	public CurrencyConversion convertCurrency(@PathVariable String from,
//			@PathVariable String to,
//			@PathVariable BigDecimal quantity
//			){
//		
//		//Hard-Coded
//		Map<String, String> uriVariables = new HashMap<>();
//		uriVariables.put("from", from);
//		uriVariables.put("to", to);
//
//		
//		ResponseEntity<CurrencyConversion> responseEntity = new RestTemplate().getForEntity(
//				"http://localhost:8000/currency-exchange/from/{from}/to/{to}", 
//				CurrencyConversion.class, 
//				uriVariables );
//		
//		CurrencyConversion response = responseEntity.getBody();
//		
//		return new CurrencyConversion(response.getId(),from,to,response.getConversionMultiple(),
//				quantity,quantity.multiply(response.getConversionMultiple()),response.getPort()); 
//	}

}
