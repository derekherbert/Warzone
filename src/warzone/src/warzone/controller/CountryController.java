package warzone.controller;

import warzone.view.*;
import warzone.model.*;
import warzone.service.CommonTool;
import warzone.service.ContinentService;
import warzone.service.CountryService;

public class CountryController {

	private CountryService d_countryService;
	private GameContext d_gameContext;
	
	public CountryController(GameContext p_gameContext) {
		d_gameContext = p_gameContext;
		d_countryService = new CountryService(p_gameContext);
	}
	
	public boolean addCountry (String p_parameters) {
		//0. parse [p_parameters] to  [ l_continentID, String l_continentName]
		if(p_parameters == null){			
			GenericView.printError("Missing valid parameters.");
			return false;
		}

		int l_countryID = -1, l_continentID = -1;
		String[] l_parameters = CommonTool.conventToArray(p_parameters);
		if(l_parameters.length == 2 ) {			
			l_countryID = CommonTool.parseInt(l_parameters[0]);
			l_continentID = CommonTool.parseInt(l_parameters[1]);
		}
		if(l_countryID == -1 || l_continentID == -1 ){
			GenericView.printError("Missing valid parameters.");
			return false;
		}
		else
			return addCountry(l_countryID, l_continentID);
	}
	
	/**
	 * Performs the action for the user command: editcountry -add countryID continentID
	 */
	public boolean addCountry (int p_countryID, int p_continentID) {		
		return d_countryService.addCountryToContient(p_countryID, p_continentID);		
	}
	
	public boolean removeCountry(String p_parameters) {
		//0. parse [p_parameters] 
		if(p_parameters == null)
		{			
			GenericView.printError("Missing valid parameters.");
			return false;
		}

		int l_countryID = CommonTool.parseInt(p_parameters);
		
		if(l_countryID == -1 ){
			GenericView.printError("Missing valid parameters.");
			return false;
		}
		
		//1. remove continent from ContinentService by id
		return removeCountry(l_countryID);
	}	
	
	
	/**
	 * Performs the action for the user command: editcountry -remove countryID
	 */
	public boolean removeCountry (int p_countryID) {
		return d_countryService.remove(p_countryID);		
	}
}
