package warzone.state;

import org.junit.Test;

import warzone.model.GameContext;
import warzone.model.GamePhase;
import warzone.service.GameEngine;

/** 
 * Test class for reinforcement phase
 */
public class ReinforcementTest {
	@Test
	public void inputNextCommand() {
		GameContext l_gameContext = GameContext.getGameContext();
		GameEngine l_gameEngine = GameEngine.getGameEngine(l_gameContext);
		Reinforcement l_reinforcementState = new Reinforcement(l_gameEngine);
		l_gameEngine.setPhase(l_reinforcementState);
		l_reinforcementState.next();
		assert(l_gameEngine.getPhase().getGamePhase()==GamePhase.IssueOrder);
	}
}
