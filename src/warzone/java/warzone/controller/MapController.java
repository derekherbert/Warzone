package warzone.controller;

import warzone.service.MapService;
import warzone.view.*;
import warzone.model.*;

import java.util.Map;

public class MapController {

	private GameContext d_gameContext;

	public MapController(GameContext p_gameContext) {
		d_gameContext = p_gameContext;
	}
	
	/**
	 * Performs the action for the user command: showmap
	 * 
	 * Displays the map as text, showing all continents and countries and their respective neighbors.
	 */
	public void showMap () {

		GenericView.printContinentTable(d_gameContext.getContinents());
		GenericView.printCountryTable(d_gameContext.getCountries());
	}
	
	/**
	 * Performs the action for the user command: savemap filename
	 * 
	 * Save a map to a text file exactly as edited (using the "domination" game map format).
	 */
	public boolean saveMap (String fileName) {
		
		// TODO Auto-generated method stub
		
		return false;
	}
	
	/**
	 * Performs the action for the user command: editmap filename
	 * 
	 * Load a map from an existing "domination" map file, or create a new map from scratch if the file does not exist
	 */
	public boolean editMap (String fileName) {
		
		// TODO Auto-generated method stub
		
		return false;
	}
	
	/**
	 * Performs the action for the user command: validatemap
	 * 
	 * Verification of map correctness. The map should be automatically validated upon loading 
	 * and before saving (at least 3 types of incorrect maps). The validatemap command can be 
	 * triggered any time during map editing. 
	 */
	public boolean validateMap () {
		
		// TODO Auto-generated method stub
		
		return false;
	}

}
