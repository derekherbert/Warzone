package warzone.state;

import org.junit.Test;

import warzone.model.GameContext;
import warzone.model.GamePhase;
import warzone.service.GameEngine;

/** 
 * Test class for issue order phase
 */
public class IssueOrderTest {
	
	@Test
	public void inputNextCommand() {
		GameContext l_gameContext = GameContext.getGameContext();
		GameEngine l_gameEngine = GameEngine.getGameEngine(l_gameContext);
		IssueOrder l_issueOrderState = new IssueOrder(l_gameEngine);
		l_gameEngine.setPhase(l_issueOrderState);
		l_issueOrderState.next();
		assert(l_gameEngine.getPhase().getGamePhase()==GamePhase.OrderExecution);
	}
	
}
