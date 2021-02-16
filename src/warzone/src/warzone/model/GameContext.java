package warzone.model;

import java.util.HashMap;
import java.util.Map;

public class GameContext {

	private Map<Integer, Player> d_players;
	private Map<Integer, Country> d_countries;
	private Map<Integer, Continent> d_continents;
	
	private static GameContext GAME_CONTEXT;
	
	private String d_mapFileName;
	private String d_mapFilePic;
	private String d_mapFileMap;
	private String d_mapFileCards;
	
	public String getMapFileCards() {
		return d_mapFileCards;
	}

	public void setMapFileCards(String mapFileCards) {
		this.d_mapFileCards = mapFileCards;
	}

	private GameContext() {
		d_players = new HashMap<Integer, Player>() ;
		d_countries = new HashMap<Integer, Country>();
		d_continents = new HashMap<Integer, Continent>();
	}		
	
	public static GameContext getGameContext() {
		if(GAME_CONTEXT == null) {
			GAME_CONTEXT = new GameContext();
		}
		return GAME_CONTEXT;
	}
	
	public Map<Integer, Player> getPlayers() {
		if(d_players == null) {
			d_players = new HashMap<Integer, Player>();
		}
		return d_players;
	}
	
	public Map<Integer, Country> getCountries() {
		if(d_countries == null) {
			d_countries = new HashMap<Integer, Country>();
		}
		return d_countries;
	}
	
	public Map<Integer, Continent> getContinents() {
		if(d_continents == null) {
			d_continents = new HashMap<Integer, Continent>();
		}
		return d_continents;
	}

	public String getMapFileName() {
		return d_mapFileName;
	}

	public void setMapFileName(String p_mapFileName) {
		this.d_mapFileName = p_mapFileName;
	}

	public String getMapFilePic() {
		return d_mapFilePic;
	}

	public void setMapFilePic(String p_mapFilePic) {
		this.d_mapFilePic = p_mapFilePic;
	}

	public String getMapFileMap() {
		return d_mapFileMap;
	}

	public void setMapFileMap(String p_mapFileMap) {
		this.d_mapFileMap = p_mapFileMap;
	}
}
