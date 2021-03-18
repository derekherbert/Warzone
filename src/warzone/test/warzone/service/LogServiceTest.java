package warzone.service;

import org.junit.AfterClass;
import org.junit.jupiter.api.Test;

import warzone.model.GameContext;
import warzone.state.MapEditor;

public class LogServiceTest {
	MapEditor d_mapEditor = new MapEditor(GameEngine.getGameEngine(GameContext.getGameContext()));
	
	@Test
	public void testLog1() {
		GameContext.getLogEntryBuffer().setPhase(new MapEditor(GameEngine.getGameEngine(GameContext.getGameContext())));
		d_mapEditor.addContinent("1 2");
	}
	
	@Test
	public void testLog2() {
		GameContext.getLogEntryBuffer().setPhase(new MapEditor(GameEngine.getGameEngine(GameContext.getGameContext())));
		d_mapEditor.addContinent("sda 2");
	}
	
	@AfterClass
	public static void afterClass() throws Exception {
		GameContext.clear();
	}
}
