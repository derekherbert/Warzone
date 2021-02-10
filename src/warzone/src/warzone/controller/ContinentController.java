package warzone.controller;

import warzone.view.*;
import warzone.model.*;

public class ContinentController {
	
	private ContinentService d_continentService;
	private GameContext d_gameContext;

	public ContinentController(GameContext p_gameContext) {
		d_gameContext = p_gameContext;
		d_continentService = new ContinentService(p_gameContext);
	}
	
	//Probably needs a reference to the warzone map object(s)
	
	/**
	 * Performs the action for the user command: editcontinent -add continentID continentName
	 */
	public boolean addContinent(int p_continentID, String p_continentName) {
		//1. create a new contient instance
		Continent l_Continent = new Continent(p_continentID, p_continentName);
		//2. TODO Auto-generated method stub
		ContinentService.add(continent);
		
		//3. render to view
		GenericView.println(continent);
		return true;
	}
	
	/**
	 * Performs the action for the user command: editcontinent -remove continentID
	 */
	public boolean removeContinent (int continentID) {
		
		// TODO Auto-generated method stub
		
		return true;
	}
}
