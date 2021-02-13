package warzone.controller;

import warzone.view.*;
import warzone.model.*;
import warzone.service.ContinentService;
import warzone.service.NeighborService;

public class NeighborController {
	
	private NeighborService d_neighborService;
	private GameContext d_gameContext;

	public NeighborController(GameContext p_gameContext) {
		d_gameContext = p_gameContext;
		d_neighborService = new NeighborService(p_gameContext);
	}

	/**
	 * Performs the action for the user command: editneighbor -add countryID neighborCountryID
	 */
	public boolean addNeighbor (int countryID, int neighborCountryID) {
		
		// TODO Auto-generated method stub
		
		return d_neighborService.add(countryID, neighborCountryID);
	}
	
	/**
	 * Performs the action for the user command: editneighbor -remove countryID neighborCountryID
	 */
	public boolean removeNeighbor (int countryID, int neighborCountryID) {
		
		// TODO Auto-generated method stub
		
		return d_neighborService.remove(countryID, neighborCountryID);
	}
}
