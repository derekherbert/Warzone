package warzone.service;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import warzone.model.GameContext;
import warzone.model.Player;

/**
 * tests for GameEngine class
 */
public class GameEngineTest {

	private GameContext d_gameContext;
	private GameEngine d_gameEngine;
	
	/**
	 * set up the context of the test class
	 */
	@Before
	public void setup() {
		
		d_gameContext = GameContext.getGameContext();
		d_gameEngine = GameEngine.getGameEngine(d_gameContext);
	}
	
	/**
	 * Test if a valid game can be started
	 */
	@Test
	public void testValidGameCanStart() {
		
		StartupService l_startupService = new StartupService(d_gameContext);
		
		Player l_p1 = new Player("p1");
		Player l_p2 = new Player("p2");
		
		l_startupService.addPlayer(l_p1);
		l_startupService.addPlayer(l_p2);
		
		l_startupService.loadMap("europe.map");
		
		l_startupService.assignCountries();
		
		assertTrue(d_gameEngine.isReadyToStart() == true);
	}
	
	/**
	 * Test if an invalid game can be started: not enough players
	 */
	@Test
	public void testInvalidGameCanStartNotEnoughPlayers() {
		
		StartupService l_startupService = new StartupService(d_gameContext);
		
		Player l_p1 = new Player("p1");
		
		l_startupService.addPlayer(l_p1);
		
		l_startupService.loadMap("europe.map");
		
		l_startupService.assignCountries();
		
		assertTrue(d_gameEngine.isReadyToStart() == false);
	}
	
	/**
	 * Test if an invalid game can be started: no assigned countries
	 */
	@Test
	public void testInvalidGameCanStartNoCountriesAssigned() {
		
		StartupService l_startupService = new StartupService(d_gameContext);
		
		Player l_p1 = new Player("p1");
		Player l_p2 = new Player("p2");
		
		l_startupService.addPlayer(l_p1);
		l_startupService.addPlayer(l_p2);
		
		l_startupService.loadMap("europe.map");
		
		assertTrue(d_gameEngine.isReadyToStart() == false);
	}
}