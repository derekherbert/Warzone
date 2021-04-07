package warzone.state;
import warzone.service.*;
import warzone.view.GenericView;
import warzone.view.HelpView;
import warzone.model.*;

/**
 *	ConcreteState of the State pattern. In this example, defines behavior
 *  for commands that are valid in this state, and for the others signifies
 *  that the command is invalid.
 */
public class IssueOrder extends GamePlay {

	/**
	 * The constructor of the class
	 * @param p_gameEngine the game engine
	 */
	public IssueOrder(GameEngine p_gameEngine) {
		super(p_gameEngine);

		this.d_gamePhase = GamePhase.IssueOrder;
	}

	/**
	 *  Call this method to go the the next state in the sequence.
	 */
	public void next() {
		d_gameEngine.setPhase(new OrderExecution(d_gameEngine));
		super.next();
	}

	/**
	 * Performs the action for user command: loadmap filename
	 *
	 * Game starts by user selection of a user-saved map file,
	 * the map should be a connected graph
	 *
	 * @param p_fileName the file to load
	 */
	public void loadMap(String p_fileName){
		 printInvalidCommandMessage();
	 }

	/**
	 * execute issue_order or execute_order
	 */
	public void play(String p_mode){
		//add all cards for each player
		if(p_mode != null && p_mode.equalsIgnoreCase("super")) {
			d_gameEngine.addCardsForAllAlivePlayers();
			GenericView.printDebug("All cards have been assigned to all the alived players.");
			
		}
		
		d_gameEngine.issueOrders();
	}
	
	/**
	 * Performs the action for user command: gameplayer -add playerName
	 *
	 * @param p_playerName player's name
	 */
	public void addPlayer(String p_playerName) {
		 printInvalidCommandMessage();
	}
	
	/**
	 * Performs the action for user command: gameplayer -remove playerName
	 *
	 * @param p_playerName player's name
	 */
	public void removePlayer(String p_playerName){
		 printInvalidCommandMessage();
	 }
	
	/**
	 * Performs the action for user command: assigncountries
	 *
	 * After user creates all the players, all countries are randomly assigned to players.
	 */
	public void assigncountries(){
		 printInvalidCommandMessage();
	 }

	/**
	 * Performs the action for user command: reinforcement
	 */
	public void reinforcement(){
		 printInvalidCommandMessage();
	 }

	/**
	 * Performs the action of order execution
	 */
	public void executeOrder(){
		 printInvalidCommandMessage();
	 }

}
