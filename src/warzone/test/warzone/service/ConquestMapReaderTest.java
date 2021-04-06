package warzone.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import warzone.model.GameContext;
import warzone.model.Router;
import warzone.state.MapEditor;
import warzone.view.MapView;

/**
 * test for ConquestMapReader
 * @author zexin
 *
 */
public class ConquestMapReaderTest {
	/**
	 * gamecontext before each test
	 */
    GameContext d_gameContext;
    
    /**
     * conquestMapReader
     */
    ConquestMapReader d_conquestMapReader;

    /**
     * clear the gamecontext before each test
     */
    @Before
    public void beforeEachTetCase(){
        GameContext.clear();
        d_gameContext = GameContext.getGameContext();
        d_conquestMapReader = new ConquestMapReader(d_gameContext);
		GameEngine.getGameEngine(d_gameContext).setPhase(new MapEditor(GameEngine.getGameEngine(d_gameContext)));
		d_gameContext.setCurrentRouter(new Router(null, null, null, "testCommand"));
    }

    /**
     * clear the gamecontext after this class run
     */
    @AfterClass
    public static void afterClass() {
        GameContext.clear();
    }
    
    /**
     * test map file "simple-starwar"
     */
    @Test
    public void testLoadConquestMap() {
    	assertTrue(d_conquestMapReader.loadConquestMap("simple-starwar.map"));
    	assertEquals(d_gameContext.getContinents().size(), 2);
    	
    	assertEquals(d_gameContext.getContinents().get(1).getCountries().size(), 2);
    	assertEquals(d_gameContext.getContinents().get(2).getCountries().size(), 3);
    	
    	assertEquals(d_gameContext.getCountries().size(), 5);
    	assertEquals(d_gameContext.getCountries().get(1).getNeighbors().size(), 1);
    	assertEquals(d_gameContext.getCountries().get(2).getNeighbors().size(), 3);
    	assertEquals(d_gameContext.getCountries().get(3).getNeighbors().size(), 2);
    	assertEquals(d_gameContext.getCountries().get(4).getNeighbors().size(), 1);
    	assertEquals(d_gameContext.getCountries().get(5).getNeighbors().size(), 1);
    	
    	assertEquals(d_gameContext.getCountries().get(1).getContinent().getContinentID(), 1);
    	assertEquals(d_gameContext.getCountries().get(2).getContinent().getContinentID(), 1);
    	assertEquals(d_gameContext.getCountries().get(3).getContinent().getContinentID(), 2);
    	assertEquals(d_gameContext.getCountries().get(4).getContinent().getContinentID(), 2);
    	assertEquals(d_gameContext.getCountries().get(5).getContinent().getContinentID(), 2);
    }
    
    /**
     * test invalid map file "no-such-conquest-map"
     */
    @Test
    public void testLoadConquestMap2() {
    	assertFalse(d_conquestMapReader.loadConquestMap("no-such-conquest-map.map"));
    }
    
    /**
     * test save map function
     * @throws IOException io exception 
     */
    @Test
    public void testSaveConquestMap2() throws IOException {
    	assertTrue(d_conquestMapReader.loadConquestMap("starwar.map"));
    	int l_continentNum = d_gameContext.getContinents().size();
    	int l_countryNum = d_gameContext.getCountries().size();
    	
    	assertTrue(d_conquestMapReader.saveConquestMap("starwar-test.map"));
    	assertEquals(d_gameContext.getContinents().size(), l_continentNum);
    	assertEquals(d_gameContext.getCountries().size(), l_countryNum);
    }
    
    /**
     * test save map function in wrong file name "This-is-not-an-valid-file-name.map"
     * @throws IOException io exception 
     */
    @Test
    public void testSaveConquestMapInvalidFileName1() throws IOException {
    	assertFalse(d_conquestMapReader.saveConquestMap("This-is-not-an-valid-file-name.map"));
    }
    
    /**
     * test save map function null file name
     * @throws IOException io exception 
     */
    @Test
    public void testSaveConquestMapInvalidFileName2() throws IOException {
    	assertFalse(d_conquestMapReader.saveConquestMap(null));
    }
    
    /**
     * test save map function with empty file name
     * @throws IOException io exception 
     */
    @Test
    public void testSaveConquestMapInvalidFileName3() throws IOException {
    	assertFalse(d_conquestMapReader.saveConquestMap(""));
    }
}
