package warzone.model;

import java.util.HashMap;
import java.util.Map;

public class GameContext {

	private static Map<Integer, Player> d_PLAYERS;
	private static Map<Integer, Country> d_COUNTRIES;
	private static Map<Integer, Continent> d_CONTINENTS;
	
	public static Map<Integer, Player> getPlayers() {
		if(d_PLAYERS == null) {
			d_PLAYERS = new HashMap<Integer, Player>();
		}
		return d_PLAYERS;
	}
	
	public static Map<Integer, Country> getCountries() {
		if(d_COUNTRIES == null) {
			d_COUNTRIES = new HashMap<Integer, Country>();
		}
		return d_COUNTRIES;
	}
	
	public static Map<Integer, Continent> getContinents() {
		if(d_CONTINENTS == null) {
			d_CONTINENTS = new HashMap<Integer, Continent>();
		}
		return d_CONTINENTS;
	}
}
