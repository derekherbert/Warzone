package warzone.controller;

import warzone.service.MapService;
import warzone.view.*;
import warzone.model.*;

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
	public GameContext showMap () {

		String l_continentTable;
		l_continentTable = MapService.displayContinentTable(d_gameContext);

		String l_countryTable;
		l_countryTable = MapService.displayContinentTable(d_gameContext);

		String l_countryNeighbourTable;
		l_countryNeighbourTable = MapService.displayCountryNeighbourTable(d_gameContext);

		GenericView.println("*****************************");
		GenericView.println("**    Continent   Table    **");
		GenericView.println("*****************************");
		GenericView.println(l_continentTable);
		GenericView.println("***************************************");
		GenericView.println("**          Country  Table           **");
		GenericView.println("***************************************");
		GenericView.println(l_countryTable);
		GenericView.println("**************************************************");
		GenericView.println("**           Country Neighbour Table            **");
		GenericView.println("**************************************************");
		GenericView.println(l_countryNeighbourTable);

		return null;
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
