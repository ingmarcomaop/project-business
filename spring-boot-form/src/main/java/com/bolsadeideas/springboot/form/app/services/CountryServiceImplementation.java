package com.bolsadeideas.springboot.form.app.services;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bolsadeideas.springboot.form.app.models.domain.Country;

@Service
public class CountryServiceImplementation implements CountryService {

	private List<Country> list;
	
	public CountryServiceImplementation() {
		this.list = Arrays.asList(
				new Country(1, "ES", "España"), 
				new Country(2, "MX","México"),
				new Country(3, "CL","Chile"),
				new Country(4, "COL", "Colombia"),
				new Country(5, "VEN", "Venezuela"),
				new Country(6, "PR", "Perú"));
	}

	@Override
	public List<Country> toListCountry() {

		return list;
	}

	@Override
	public Country getCountryById(Integer id) {
		
		Country result = null;
		
		for(Country country: this.list) { //estructura foreach
			if(id == country.getId()) {
				result = country;
				break;
			}else { continue; }
		} 
		
		return result;
	}

}
