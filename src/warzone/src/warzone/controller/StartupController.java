package warzone.controller;

import warzone.view.*;
import warzone.model.*;
import warzone.service.CommonTool;

public class StartupController {


	/**
	 * Performs the action for user command: loadmap filename
	 * 
	 * Game starts by user selection of a user-saved map file, which loads the map as a connected directed graph
	 * 
	 * @param fileName
	 * @return
	 */
	public GameContext loadMap(String fileName) {
		
		// TODO Auto-generated method stub
		
		return null;
	}
	
	public boolean addRawPlayer(String p_parameters) {
		//parse [p_parameters]
		if(p_parameters == null){			
			GenericView.printError("Missing valid parameters.");
			return false;
		}

		String l_playerName = "";
		String[] l_parameters = CommonTool.conventToArray(p_parameters);
		if(l_parameters.length == 1 ) {			
			l_playerName = l_parameters[0];
		}
		if(l_playerName == ""){
			GenericView.printError("Missing valid parameters.");
			return false;
		}

		return addPlayer(l_playerName);	
	}
	
	/**
	 * Performs the action for user command: gameplayer -add playerName
	 * 
	 * @param name
	 * @return
	 */
	public boolean addPlayer(String name) {
		
		// TODO Auto-generated method stub
		
		return false;
	}
	
	public boolean removeRawPlayer(String p_parameters) {
		//parse [p_parameters]
		if(p_parameters == null){			
			GenericView.printError("Missing valid parameters.");
			return false;
		}

		String l_playerName = "";
		String[] l_parameters = CommonTool.conventToArray(p_parameters);
		if(l_parameters.length == 1 ) {			
			l_playerName = l_parameters[0];
		}
		if(l_playerName == ""){
			GenericView.printError("Missing valid parameters.");
			return false;
		}

		return removePlayer(l_playerName);	
	}
	
	/**
	 * Performs the action for user command: gameplayer -remove playerName
	 * 
	 * @param name
	 * @return
	 */
	public boolean removePlayer(String name) {
		
		// TODO Auto-generated method stub
		
		return false;
	}
	
	/**
	 * Performs the action for user command: assigncountries
	 * 
	 * After user creates all the players, all countries are randomly assigned to players. 
	 * 
	 * @return
	 */
	public boolean assignCountries() {
		
		// TODO Auto-generated method stub
		
		return false;
	}
	
}
