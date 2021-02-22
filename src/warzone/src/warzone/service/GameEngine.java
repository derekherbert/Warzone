package warzone.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import warzone.model.*;
import warzone.view.GenericView;

/**
 * Main game loop.
 * 
 * Loop over each player for the assign reinforcements, issue orders, and execute orders main game loop phases
 * 
 */
public class GameEngine {
	private GameContext d_gameContext;	
	private static GameEngine GAME_ENGINE;
	
	private GameEngine(GameContext p_gameContext) {
		d_gameContext = p_gameContext;
	}
	
	public static GameEngine getGameEngine(GameContext p_gameContext) {
		if( GAME_ENGINE == null)
			GAME_ENGINE = new GameEngine(p_gameContext);
		return GAME_ENGINE;
	}	

	public static void main(String[] args) throws IOException {

		GameContext l_gameContext = GameContext.getGameContext();
		RouterService l_routerService =  RouterService.getRouterService(l_gameContext);
		CommandService l_commandService =  CommandService.getCommandService(l_gameContext);
		
		
		//1 welcome
		Router l_welcomeRouter = new Router(ControllerName.COMMON, "welcome");
		l_routerService.route(l_welcomeRouter);
		
		l_commandService.commandScanner(l_routerService);
	}	
	
	public boolean isReadyToStart() {
		if(this.d_gameContext == null || this.d_gameContext.getContinents().size() <1 
				|| this.d_gameContext.getCountries().size() < 1 || this.d_gameContext.getPlayers().size() < 1 )
			return false;
		else
			return true;
	}
	
	public void setGamePhase(GamePhase p_gamePhase) {
		d_gameContext.setGamePhase(p_gamePhase);		
	}
	
	public boolean play() {
		if(! isReadyToStart())
			return false;
		
		int l_loopNumber = 1;		
		while( !isGameEnded() && l_loopNumber <= 100) {
			startTurn();
			l_loopNumber ++;
		}
		
		return isGameEnded();
	}
	
	
	private void startTurn() {		
		assignReinforcements();
		issueOrders();
		executeOrders();		
	}
	
	private boolean isGameEnded() {
		//check and update PlayerStatus		
		//set p_isLoser = true, when the player does not have any country
		int l_alivePlayers = 0;
		for(Player l_player :d_gameContext.getPlayers().values() ){
			if(l_player.getConqueredCountries().size() == 0) {
				l_player.setIsAlive(false);
				l_alivePlayers ++;
			}
		}		
		return l_alivePlayers <= 1;
	}
	

	/**
	 * Assign each player the correct number of reinforcement armies according to the Warzone rules.
	 */
	private void assignReinforcements() {
		d_gameContext.getPlayers().forEach((k, player) -> {
			if(player.getIsAlive())
				player.assignReinforcements();
		});
	}
	
	/**
	 * The GameEngine class calls the issue_order() method of the Player. This method will wait for the following command, 
	 * then create a deploy order object on the players list of orders, then reduce the number of armies in the 
	 * players reinforcement pool. The game engine does this for all players in round-robin fashion until all the players 
	 * have placed all their reinforcement armies on the map.
	 */
	private void issueOrders() {

		d_gameContext.getPlayers().forEach((k, player) -> {
			if(player.getIsAlive())
				player.issue_order();
		});			
	}
	
	
	/**
	 * The GameEngine calls the next_order() method of the Player. Then the Order object�s execute() method is called 
	 * which will enact the order. 
	 * <ol>
	 * <li>get the max number of the orders own by a single player</li>
	 * <li>excute the orders from player's order list in round-robin fashion</li>
	 * </ol>
	 */
	private void executeOrders() {

		//1. get the max number of the orders in a player.		
		int l_maxOrderNumber = 0;	
		for(Player l_player :d_gameContext.getPlayers().values() ){
			if(l_player.getIsAlive()) {
				if( l_player.getOrders().size() > l_maxOrderNumber)
					l_maxOrderNumber = l_player.getOrders().size();				
			}		
		}

		//2. excute the orders
		int l_roundIndex = 1;
		while(l_roundIndex <= l_maxOrderNumber ){
			d_gameContext.getPlayers().forEach((k, player) -> {
				if(player.getIsAlive()) {
					Order l_order = player.next_order();
					if(l_order != null)
						l_order.execute();
				}				
			});
			l_roundIndex ++;			
		}	
		
	}	 
}
