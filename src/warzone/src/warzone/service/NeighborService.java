package warzone.service;

import java.util.Map;

import warzone.model.Country;
import warzone.model.GameContext;

public class NeighborService {
	
	private GameContext d_gameContext;

	public NeighborService(GameContext p_gameContext) {
		d_gameContext = p_gameContext;
	}
	
	public boolean add(int countryID, int neighborCountryID) {
		Map<Integer,Country> countryMap=d_gameContext.getCountries();
		if(!(countryMap.containsKey(countryID)&&countryMap.containsKey(neighborCountryID))) {
			return false;
		}
		
		Country c1=countryMap.get(countryID);
		Country c2=countryMap.get(neighborCountryID);
		c1.getNeighbors().put(neighborCountryID, c2);
		c2.getNeighbors().put(countryID,c1);
		
		return true;
	}
	
	public boolean remove(int countryID, int neighborCountryID) {
		Map<Integer,Country> countryMap=d_gameContext.getCountries();
		if(!(countryMap.containsKey(countryID)&&
				countryMap.get(countryID).getNeighbors().containsKey(neighborCountryID))) {
			return false;
		}
		countryMap.get(countryID).getNeighbors().remove(neighborCountryID);
		
		if(countryMap.get(neighborCountryID).getNeighbors().containsKey(countryID)) {
			countryMap.get(neighborCountryID).getNeighbors().remove(countryID);
		}
		
		return true;
	}
}
