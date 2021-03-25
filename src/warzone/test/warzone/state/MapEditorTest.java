package warzone.state;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import warzone.model.GameContext;
import warzone.model.GamePhase;
import warzone.model.Router;
import warzone.service.GameEngine;

/**
 * test class for MapEditor
 */
public class MapEditorTest {

	@Test
	public void addAndRemoveContinent() {
		GameContext l_gameContext = GameContext.getGameContext();
		GameEngine l_gameEngine = GameEngine.getGameEngine(l_gameContext);
		MapEditor l_mapEditorState = new MapEditor(l_gameEngine);
		l_gameEngine.setPhase(l_mapEditorState);
		l_gameContext.setCurrentRouter(new Router(null, null, null));
		
		l_mapEditorState.addContinent(1,5);
		assert(l_gameContext.getContinents().size()==1);
		l_mapEditorState.removeContinent(1);
		assert(l_gameContext.getContinents().size()==0);
	}
	
	@Test
	public void addAndRemoveCountry() {
		GameContext l_gameContext = GameContext.getGameContext();
		GameEngine l_gameEngine = GameEngine.getGameEngine(l_gameContext);
		MapEditor l_mapEditorState = new MapEditor(l_gameEngine);
		l_gameEngine.setPhase(l_mapEditorState);
		l_gameContext.setCurrentRouter(new Router(null, null, null));
		l_mapEditorState.addContinent(1,5);
		l_mapEditorState.addCountry(101, 1);
		l_mapEditorState.addCountry(102, 1);
		assert(l_gameContext.getCountries().size()==2);
		assert(l_gameContext.getContinents().get(1).getCountries().size()==2);
		l_mapEditorState.removeCountry(101);
		assert(l_gameContext.getCountries().size()==1);
		assert(l_gameContext.getContinents().get(1).getCountries().size()==1);
	}
	
	@Test
	public void addAndRemoveNeighbor() {
		GameContext l_gameContext = GameContext.getGameContext();
		GameEngine l_gameEngine = GameEngine.getGameEngine(l_gameContext);
		MapEditor l_mapEditorState = new MapEditor(l_gameEngine);
		l_gameEngine.setPhase(l_mapEditorState);
		l_gameContext.setCurrentRouter(new Router(null, null, null));
		l_mapEditorState.addContinent(1,5);
		l_mapEditorState.addCountry(101, 1);
		l_mapEditorState.addCountry(102, 1);
		l_mapEditorState.addNeighbor(101, 102);
		assert(l_gameContext.getCountries().get(101).getNeighbors().size()==1);
		assert(l_gameContext.getCountries().get(102).getNeighbors().size()==1);
		l_mapEditorState.removeNeighbor(102,101);
		assert(l_gameContext.getCountries().get(101).getNeighbors().size()==0);
		assert(l_gameContext.getCountries().get(102).getNeighbors().size()==0);
	}
	
	@Test
	public void inputNextCommand() {
		GameContext l_gameContext = GameContext.getGameContext();
		GameEngine l_gameEngine = GameEngine.getGameEngine(l_gameContext);
		MapEditor l_mapEditorState = new MapEditor(l_gameEngine);
		l_gameEngine.setPhase(l_mapEditorState);
		l_mapEditorState.next();
		assert(l_gameEngine.getPhase().getGamePhase()==GamePhase.STARTUP);
	}
}