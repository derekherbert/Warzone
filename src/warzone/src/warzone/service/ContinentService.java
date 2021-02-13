package warzone.service;

import java.util.Map;

import warzone.model.*;

public class ContinentService {
	
	private GameContext d_gameContext;

	public ContinentService(GameContext p_gameContext) {
		d_gameContext = p_gameContext;
	}
	
	public boolean add(Continent p_continent) {
		//0. add the item to
		Map<Integer,Continent> map=d_gameContext.getContinents();
		int key=p_continent.getContinentID();
		if(map.containsKey(key)) {
			return false;
		}
		map.put(key, p_continent);
		return true;
	}
	
	public Continent remove(int continentID) {
		Map<Integer,Continent> map=d_gameContext.getContinents();
		if(!map.containsKey(continentID)) {
			return null;
		}
		return map.remove(continentID);
	}
}
