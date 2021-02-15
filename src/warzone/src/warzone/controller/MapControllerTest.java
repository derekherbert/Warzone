package warzone.controller;

import static org.junit.Assert.*;

import org.junit.Test;

import warzone.model.GameContext;

public class MapControllerTest {

	@Test
	public void test() {
		
		GameContext gameContext = GameContext.getGameContext();
		MapController mapController = new MapController(gameContext);
		
		mapController.editMap("europe.map");
		
		System.out.println("Map File Name: " + gameContext.getMapFileName());
		System.out.println("Map File Pic: " + gameContext.getMapFilePic());
		System.out.println("Map File Map: " + gameContext.getMapFileMap());
		System.out.println("Map File Name: " + gameContext.getMapFileCards());
		
		System.out.println();
		
		System.out.println("Number of Continents: " + gameContext.getContinents().size());
		System.out.println("Number of Countries: " + gameContext.getCountries().size());
	}

}
