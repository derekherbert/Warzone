package warzone.controller;

import warzone.view.*;
import warzone.model.*;
import warzone.service.ContinentService;
import warzone.service.CountryService;

public class CountryController {

	private CountryService d_countryService;
	private GameContext d_gameContext;
	
	public CountryController(GameContext p_gameContext) {
		d_gameContext = p_gameContext;
		d_countryService = new CountryService(p_gameContext);
	}
	
	/**
	 * Performs the action for the user command: editcountry -add countryID continentID
	 */
	public boolean addCountry (int countryID, int continentID) {
		
		// Create country object.Missing the necessary parameters?
		Country country=new Country();
		country.setOwnerID(continentID);
		
		// Add to service
		d_countryService.add(country);
		
		return false;
	}
	
	/**
	 * Performs the action for the user command: editcountry -remove countryID
	 */
	public boolean removeCountry (int countryID) {
		
		if(d_countryService.remove(countryID)==null) {
			return false;
		}else {
			return true;
		}
	}
}
