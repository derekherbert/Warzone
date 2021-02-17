package warzone.service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import warzone.controller.MapController;
import warzone.controller.StartupController;
import warzone.model.Continent;
import warzone.model.Country;
import warzone.model.GameContext;
import warzone.model.Player;

public class StartupServiceTest {

	GameContext d_gameContext;
	StartupController d_startupController;
	
	@Before
	public void setup() {
		
		d_gameContext = GameContext.getGameContext();
		d_gameContext.getContinents().clear();
		d_gameContext.getCountries().clear();
		d_gameContext.getPlayers().clear();
	}
	
	@Test
	public void testLoadMap() {
		
		d_startupController = new StartupController(d_gameContext);
		d_startupController.loadMap("europe.map");
		
		System.out.println("Map File Name: " + d_gameContext.getMapFileName());
		System.out.println("Map File Pic: " + d_gameContext.getMapFilePic());
		System.out.println("Map File Map: " + d_gameContext.getMapFileMap());
		System.out.println("Map File Name: " + d_gameContext.getMapFileCards());
		
		System.out.println();
		
		System.out.println("Number of Continents: " + d_gameContext.getContinents().size());
		System.out.println("Number of Countries: " + d_gameContext.getCountries().size());
		System.out.println();
	}
	
	@Test
	public void testAssignCountries() {
		
		d_gameContext.getPlayers().put("player1", new Player("player1"));
		d_gameContext.getPlayers().put("player2", new Player("player2"));
		d_gameContext.getPlayers().put("player3", new Player("player3"));
		d_gameContext.getPlayers().put("player4", new Player("player4"));
		
		d_gameContext.getCountries().put(1, new Country(1, "country01", 5, 5, null));
		d_gameContext.getCountries().put(2, new Country(2, "country02", 5, 5, null));
		d_gameContext.getCountries().put(3, new Country(3, "country03", 5, 5, null));
		d_gameContext.getCountries().put(4, new Country(4, "country04", 5, 5, null));
		d_gameContext.getCountries().put(5, new Country(5, "country05", 5, 5, null));
		d_gameContext.getCountries().put(6, new Country(6, "country06", 5, 5, null));
		d_gameContext.getCountries().put(7, new Country(7, "country07", 5, 5, null));
		d_gameContext.getCountries().put(8, new Country(8, "country08", 5, 5, null));
		d_gameContext.getCountries().put(9, new Country(9, "country09", 5, 5, null));
		d_gameContext.getCountries().put(10, new Country(10, "country10", 5, 5, null));
		
		d_startupController = new StartupController(d_gameContext);
		d_startupController.assignCountries();
		
		//Create a list of playerIDs from the game context and shuffle their order
		List<String> playerNames = new ArrayList<String>(d_gameContext.getPlayers().keySet());
		List<Integer> neighborIDs;
		Player player;
		
		for(String playerID : playerNames) {	
			
			player = d_gameContext.getPlayers().get(playerID);
			System.out.print(player.getName() + " neighbors: ");
			
			neighborIDs = new ArrayList<Integer>(player.getConqueredCountries().keySet());
			
			for(Integer neighborID : neighborIDs) {
				
				System.out.print(d_gameContext.getCountries().get(neighborID).getCountryName() + " ");
			}
			
			System.out.println();
		}
	}
}