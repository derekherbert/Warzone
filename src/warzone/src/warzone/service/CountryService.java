package warzone.service;

import java.util.Map;

import warzone.model.Continent;
import warzone.model.Country;
import warzone.model.GameContext;

public class CountryService {
	private GameContext d_gameContext;

	public CountryService(GameContext p_gameContext) {
		d_gameContext = p_gameContext;
	}
	
	public boolean add(Country country) {
		Map<Integer,Country> countryMap=d_gameContext.getCountries();
		int countryKey=country.getCountryID();
		Map<Integer,Continent> continentMap=d_gameContext.getContinents();
		int continentKey=country.getOwnerID();
		if(countryMap.containsKey(countryKey)||
				continentMap.containsKey(continentKey)) {
			return false;
		}
		countryMap.put(countryKey, country);
		continentMap.get(continentKey).getCountries().put(countryKey, country);
		return true;
	}
	
	public Country remove(int countryID) {
		Map<Integer,Country> countryMap=d_gameContext.getCountries();
		if(!countryMap.containsKey(countryID)) {
			return null;
		}
		Country country=countryMap.remove(countryID);
		Map<Integer,Continent> continentMap=d_gameContext.getContinents();
		int continentKey=country.getOwnerID();
		if(continentMap.containsKey(continentKey)) {
			continentMap.remove(continentKey);
		}
		return country;
	}
}
