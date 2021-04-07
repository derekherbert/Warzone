package warzone.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import warzone.model.*;

/**
 * This class will provide controllers with service associating with starup
 *
 */
public class StartupService {

	/**
	 * game context
	 */
	private GameContext d_gameContext;

	/**
	 * log entry buffer
	 */
	private LogEntryBuffer d_logEntryBuffer;

	/**
	 * This constructor can initiate the game context of current instance.
	 * @param p_gameContext the current game context
	 */
	public StartupService(GameContext p_gameContext) {
		d_gameContext = p_gameContext;
		d_logEntryBuffer = d_gameContext.getLogEntryBuffer();
	}
	
	/**
	 * Add player, maximum number is 5.
	 * @param p_player Player object
	 * @return true if add success else false
	 */
	public boolean addPlayer(Player p_player) {
		//0. add the item to
		Map<String,Player> l_players=d_gameContext.getPlayers();
		if(p_player != null 
				&& p_player.getName()!="" 
				&& l_players.size()<= 5 
				&& !l_players.containsKey(p_player.getName())) {			
			l_players.put(p_player.getName(), p_player);
			return true;
		}
		return false;
	}
	
	/**
	 * Remove player by name
	 * @param p_playerName name of player
	 * @return true if remove success else false
	 */
	public boolean removePlayer(String p_playerName) {
		if(p_playerName != null && d_gameContext.getPlayers().containsKey(p_playerName)) {
			Player l_player = d_gameContext.getPlayers().get(p_playerName);
			for( Country l_country : l_player.getConqueredCountries().values() ) 
				l_country.setCountryState(CountryState.Initial, null);
			d_gameContext.getPlayers().remove(p_playerName);
			return true;
		}
		return false;
	}

	/**
	 * Performs the action for user command: assigncountries
	 * 
	 * After user creates all the players, all countries are randomly assigned to players. 
	 * 1-reset the countries
	 * 2-assign countries
	 * 
	 * @return true if successfully assign the countries, otherwise return false
	 */
	public boolean assignCountries() {
		//Make sure there are more than 1 player
		//and there are enough countries to distribute between all the players
		if( d_gameContext.getPlayers().size() < 2 || d_gameContext.getPlayers().size() > d_gameContext.getCountries().size() ) {
			d_logEntryBuffer.logAction("ERROR", "The game should have 2 players at least, and countriese number is greater than players number.");
			return false;
		}
		//reset the countries list and for each player.
		for( Player l_player : d_gameContext.getPlayers().values()) {
			l_player.cleanConqueredCountries();
		}
		//rest all the owner for countries
		for( Country l_countryTemp: d_gameContext.getCountries().values()) {
			l_countryTemp.setCountryState(CountryState.Initial,null);
		}		
		
		//Each player will be assigned the same number of countries. Leftover countries will be unassigned (neutral)
		//Create a list of playerIDs from the game context and shuffle their order
		List<String> l_playerNames = new ArrayList<String>(d_gameContext.getPlayers().keySet());
		Collections.shuffle(l_playerNames);
		
		//Create a list of countryIDs from the game context and shuffle their order
		List<Integer> l_countryIDs = new ArrayList<Integer>(d_gameContext.getCountries().keySet());
		Collections.shuffle(l_countryIDs);
				
		//Looping variables
		Country l_country;
		Player l_player;
		int l_ctr = 0;
		int l_playerIndex = 0;
		
		//Loop through each country to assign to a random player
		for(Integer l_countryID : l_countryIDs) {			
			//Reset the index once each player has been assigned a country
			if(l_playerIndex >= l_playerNames.size()) {
				l_playerIndex = 0;
			}
			
			l_country = d_gameContext.getCountries().get(l_countryID);
			l_player = d_gameContext.getPlayers().get(l_playerNames.get(l_playerIndex));

			l_country.setCountryState(CountryState.Occupied, l_player);

			//Update the looping variables
			l_playerIndex++;
			l_ctr++;
		}
		
		return true;
	}
}
