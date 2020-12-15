package io.pivotal.workshop.controller;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.pivotal.workshop.model.CurrencyConversion;

@RestController
public class CurrencyConversionController {
	
	@GetMapping("/currency-converter/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversion convertCurrency(@PathVariable String from,
			@PathVariable String to,
			@PathVariable BigDecimal quantity
			){
		
		//Hard-Coded
		Map<String, String> uriVariables = new HashMap<>();
		uriVariables.put("from", from);
		uriVariables.put("to", to);

		
		ResponseEntity<CurrencyConversion> responseEntity = new RestTemplate().getForEntity(
				"http://localhost:8000/currency-exchange/from/{from}/to/{to}", 
				CurrencyConversion.class, 
				uriVariables );
		
		CurrencyConversion response = responseEntity.getBody();
		
		return new CurrencyConversion(response.getId(),from,to,response.getConversionMultiple(),
				quantity,quantity.multiply(response.getConversionMultiple()),response.getPort()); 
	}

}
