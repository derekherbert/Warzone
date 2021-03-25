package warzone.state;

import org.junit.Test;

import warzone.model.GameContext;
import warzone.model.GamePhase;
import warzone.service.GameEngine;

/** 
 * Test class for order execution phase
 */
public class OrderExecutionTest {
	@Test
	public void inputNextCommand() {
		GameContext l_gameContext = GameContext.getGameContext();
		GameEngine l_gameEngine = GameEngine.getGameEngine(l_gameContext);
		OrderExecution l_orderExecitonState = new OrderExecution(l_gameEngine);
		l_gameEngine.setPhase(l_orderExecitonState);
		l_orderExecitonState.next();
		assert(l_gameEngine.getPhase().getGamePhase()==GamePhase.STARTUP);
	}
}
