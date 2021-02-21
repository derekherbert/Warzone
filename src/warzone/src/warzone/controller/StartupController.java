package warzone.controller;

import warzone.view.*;
import warzone.model.*;
import warzone.service.MapService;
import warzone.service.StartupService;

public class StartupController {

	private StartupService d_startupService;
	private GameContext d_gameContext;

	public StartupController(GameContext p_gameContext) {
		
		d_gameContext = p_gameContext;
		d_startupService = new StartupService(p_gameContext);
	}
	

	/**
	 * Performs the action for user command: gameplayer -add playerName
	 * 
	 * @param name player's name
	 * @return true if add success else false
	 */
	public boolean addPlayer(String p_playerName) {
		//1. create a new player instance
		Player l_player = new Player(p_playerName);
		
		//2. add player to PlayerService
		boolean l_ok=d_startupService.addPlayer(l_player);
		
		//3. render to view
		if(l_ok) {
			GenericView.printSuccess( String.format("Player [%s] was added successfully.", l_player.getName()) );
		}else {
			GenericView.printError( String.format("Player [%s] was added failed.", l_player.getName()) );
		}
		return l_ok;
	}
	
	/**
	 * Performs the action for user command: gameplayer -remove playerName
	 * 
	 * @param name player's name
	 * @return true if remove success else false
	 */
	public boolean removePlayer(String p_playerName) {
		if( d_startupService.removePlayer(p_playerName)) {
			GenericView.printSuccess( String.format("Continent ID [%s] was removed successfully.", p_playerName) );
			return true;
		}else {
			GenericView.printWarning( String.format("Failed to remove Continent ID [%s].", p_playerName ) );
			return false;
		}
	}
	
	/**
	 * Performs the action for user command: loadmap filename
	 * 
	 * Game starts by user selection of a user-saved map file, which loads the map as a connected directed graph
	 * 
	 * @param p_fileName
	 * @return if load map success
	 */
	public boolean loadMap(String p_fileName) {
		
		return d_startupService.loadMap(p_fileName);
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
		
		boolean result = d_startupService.assignCountries();
		
		if(result == false) {
			
			GenericView.printError("There must be at least the same number of countries as players");
		}
		
		return false;
	}
	
}
