package warzone.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import warzone.controller.*;
import warzone.model.*;
import warzone.state.Phase;
import warzone.view.GenericView;

/**
 * This class can offer service related router to controllers.
 *
 */
public class RouterService {
		
	private static RouterService ROUTER_SERVICE;
	
	private GameContext d_gameContext;
	private GameEngine d_gameEngine;
	private Phase d_gamePhase;
	/**
	 * the constructor of it, only can be used inside this class.
	 * @param p_gameContext the current game context
	 */
	private RouterService(GameEngine p_gameEngine) {
		d_gameContext = p_gameEngine.getGameContext();
		d_gameEngine = p_gameEngine;
		d_gamePhase = p_gameEngine.getPhase();
	}	
	
	/**
	 * This method will return a routerService instance and create it if the instance
	 * is null.
	 * @param p_gameContext the game context instance
	 * @return the RouterService instance
	 */
	public static RouterService getRouterService( GameEngine p_gameEngine) {
		if( ROUTER_SERVICE == null)
			ROUTER_SERVICE = new RouterService(p_gameEngine);
		return ROUTER_SERVICE;
	}
//	/**
//	 * Check if current game phase is included in the given game phases list.
//	 * @param p_gamePhases Given game phases list
//	 * @return True if included, otherwise false.
//	 */
//	private boolean getIsContainCurrentPhase(List<GamePhase> p_gamePhases) {
//		if( d_gameContext.getIsContainCurrentPhase(p_gamePhases))
//			return true;
//		else {
//			GenericView.printWarning("The command is not valid in the current phase: " + d_gameContext.getGamePhase());
//			return false;
//		}
//	}
	
	/**
	 * This method will parse a single console commands entered by the user and call the corresponding controller by controller name
	 * @param p_router the Router parsed from the command
	 * @throws IOException exception from reading the commands
	 */
	public void route(Router p_router) throws IOException{

		switch(p_router.getActionName().toLowerCase()) {
			case "next":
				d_gamePhase.next();
				break;
			case "addcontinent":
				d_gamePhase.addContinent(p_router.getActionParameters());
				break;
			case "removecontinent":
				d_gamePhase.removeContinent(p_router.getActionParameters());
				break;
			case "addcountry":
				d_gamePhase.addCountry(p_router.getActionParameters());
				break;
			case "removecountry":
				d_gamePhase.removeCountry(p_router.getActionParameters());
				break;
			case "showmap":
				d_gamePhase.showMap();
				break;
			case "savemap":
				d_gamePhase.saveMap(p_router.getActionParameters());
				break;
			case "editmap":
				d_gamePhase.editMap(p_router.getActionParameters());
				break;
			case "validatemap":
				d_gamePhase.validateMap();
				break;
			case "addneighbor":
				d_gamePhase.addNeighbor(p_router.getActionParameters());
				break;
			case "removeneighbor":
				d_gamePhase.removeNeighbor(p_router.getActionParameters());
				break;
			case "addplayer":
				d_gamePhase.addPlayer(p_router.getActionParameters());
				break;
			case "removeplayer":
				d_gamePhase.removePlayer(p_router.getActionParameters());
				break;
			case "loadmap":
				d_gamePhase.loadMap(p_router.getActionParameters());
				break;
			case "populatecountries":
				d_gamePhase.populatecountries();
				break;
			case "reinforcement":
				d_gamePhase.reinforcement();
				break;
			case "issueOrder":
				d_gamePhase.issueOrder();
				break;
			case "executeOrder":
				d_gamePhase.executeOrder();
				break;
		}		
	}
	
	/**
	 * This method can parse a list of commands
	 * @param p_routers the list of router
	 */
	public void route(List<Router> p_routers) {
		if(p_routers != null) {
			p_routers.forEach((l_router) ->{
				try {
					GenericView.printDebug("Excuting router: " + l_router.toString() );
					route(l_router);
				}
				catch(Exception l_ex){
					GenericView.printError("Exception occur: " + l_ex.toString());
				}
			});			
		}
	}
	
	/**
	 * This method parses the command entered by the player, and construct corresponding Router
	 * by different commands
	 * e.g.
	 * editcountry -add countryID continentID -remove countryID
	 * new Router(ControllerName.COUNTRY, "add", "countryID continentID");
	 * new Router(ControllerName.COUNTRY, "remove", "countryID");
	 * @param p_command command entered by the player
	 * @return error Router if the command is wrong
	 */
	public List<Router> parseCommand(String p_command) {
		List<Router> l_routerList = new LinkedList<Router>();
		
		//validation
		if( p_command == null || p_command.trim().equals("") ){
			l_routerList.add(createErrorRouter(ErrorType.MISSING_COMMAND.toString()));
			return l_routerList;
		}
			
		GenericView.printDebug("parseCommand: start to work on command: " + p_command);
		
		// remove prefix whitespace and convert the String to lower case 
		p_command = p_command.toLowerCase().trim();
				
		// split command with any number of whitespace
		String[] l_commandArray = p_command.split("\\s+");
		
		String l_firstWord = "," + l_commandArray[0] + ",";
		// TODO move these commands into the properties file
		String l_complexCommand = ",editcontinent,editcountry,editneighbor,gameplayer,";
		String l_simpleCommand = ",loadmap,editmap,savemap,assigncountries,validatemap,showmap,help,play,reboot,startup,mapeditor,";
		 if(l_simpleCommand.indexOf(l_firstWord) > -1) {
				//simple command with only one router
				GenericView.printDebug("parseCommand: start to work on simple command: " + p_command);
				l_routerList.add(parseSimpleCommand(l_commandArray));				
		}		 
		else if(l_complexCommand.indexOf(l_firstWord) > -1) {
			//complex command with multiple routers
			GenericView.printDebug("parseCommand: start to work on complex command: " + p_command);
			l_routerList = parseComplexCommand(l_commandArray);
		}
		else {
			l_routerList.add(createErrorRouter(ErrorType.NO_SUCH_COMMAND.toString()));
			return l_routerList;
		}
		return l_routerList;
	}	
	
	/**
	 * A command can be divided into two types, complex command and simple command. 
	 * This method is responsible to parse complex commands, such as editCountry and editContinent, 
	 * and convert the command into a list of Router
	 * @param p_commandArray command divided by whitespace
	 * @return a Router list representing the command
	 */
	private List<Router> parseComplexCommand(String[] p_commandArray) {
		List<Router> l_routers = new LinkedList<Router>();
		List<Action> l_actions = parseCommandToAction(p_commandArray);		
		 
		if(l_actions.isEmpty() ) {
			l_routers.add(createErrorRouter(ErrorType.MISSING_PARAMETER.toString()));
			GenericView.printDebug("parseComplexCommand: Empty Action" );
			return l_routers;
		}
		
		ControllerName l_controllerName = ControllerName.COMMON;		
		switch (p_commandArray[0]) {
			case "editcontinent":
				l_controllerName = ControllerName.CONTINENT;
				break;
			case "editcountry":
				l_controllerName = ControllerName.COUNTRY;				
				break;
			case "editneighbor":
				l_controllerName = ControllerName.NEIGHBOR;				
				break;
			case "gameplayer":
				l_controllerName = ControllerName.STARTUP;				
				break;
		}
		GenericView.printDebug("ControllerName is :" + l_controllerName.toString() );
		// if the action is not equal to 'add' or 'remove', we return an error router
		for(Action l_action: l_actions) {
			//TODO add it in the property file
			String l_actionArray = "-add,-remove";
			if(l_actionArray.indexOf(l_action.getAction()) > -1) { 
				l_routers.add(new Router(l_controllerName, l_action.getAction(), l_action.getParameters()));
				GenericView.printDebug("Add an action to a router");
			}
			else {
				l_routers = new LinkedList<Router>();
				l_routers.add(createErrorRouter(ErrorType.BAD_OPTION.toString()));
				GenericView.printDebug("Meet an error when adding an action to a router");
				return l_routers;
			}
		}		
		return l_routers;
	}
	
	/**
	 * This method is responsible to parse simple commands, such as showmap and validatemap, 
	 * and convert the command into a list of Router
	 * @param p_commandArray command divided by whitespace
	 * @return a Router list representing the command
	 */
	private Router parseSimpleCommand(String[] p_commandArray) {
		// create the router according to the command
		Router l_router = null;
		// the first element of commandArray is command
		switch (p_commandArray[0]) {
			case "reboot":
			case "startup":
			case "mapeditor":
				l_router = new Router(ControllerName.COMMON, "changephase",p_commandArray[0]);
				break;
			case  "help":
				l_router = new Router(ControllerName.COMMON, "help");
				break;		
			case  "showmap":
					l_router = new Router(ControllerName.MAP, "showmap");
				break;
			case  "validatemap":
				l_router =  new Router(ControllerName.MAP, "validatemap");
				break;
			case  "play":
				l_router =  new Router(ControllerName.GAMEPLAY, "play");
				break;				
			case  "assigncountries":
				l_router =  new Router(ControllerName.STARTUP, "assigncountries");
				break;
			case  "savemap":
				if (p_commandArray.length == 1) {
					return createErrorRouter(ErrorType.MISSING_PARAMETER.toString());
				}
				if(p_commandArray.length == 2 ) {
					l_router =  new Router(ControllerName.MAP, "savemap", p_commandArray[1]);
				}
				else {
					return createErrorRouter(ErrorType.TOO_MUCH_PARAMETERS.toString());
				}
				break;
			case  "editmap":
				if (p_commandArray.length == 1) {
					return createErrorRouter(ErrorType.MISSING_PARAMETER.toString());
				}
				if(p_commandArray.length == 2 ) {
					l_router =  new Router(ControllerName.MAP, "editmap", p_commandArray[1]);
				}
				else {
					return createErrorRouter(ErrorType.TOO_MUCH_PARAMETERS.toString());
				}
				break;
			case  "loadmap":
				if (p_commandArray.length == 1) {
					return createErrorRouter(ErrorType.MISSING_PARAMETER.toString());
				}
				if(p_commandArray.length == 2 ) {
					l_router =  new Router(ControllerName.STARTUP, "loadmap", p_commandArray[1]);
				}
				else {
					return createErrorRouter(ErrorType.TOO_MUCH_PARAMETERS.toString());
				}
				break;
			//TODO other routers for simple commands
		}
		
		return l_router;
	}

	/**
	 * This method can extract options and corresponding parameters from the command and construct
	 * Action for every option.
	 * @param p_commandArray command divided by whitespace
	 * @return a list of Action representing option and parameters
	 */
	private List<Action> parseCommandToAction(String[] p_commandArray) {
		List<Action> l_actions = new LinkedList<Action>();

		// in this loop we recognize option and according parameter
		for(int l_tempi = 1; l_tempi < p_commandArray.length; l_tempi++) {
			// judge that if p_commandArray[i] is an option
			if(p_commandArray[l_tempi].charAt(0) == '-') {
				// if an option is the last element of the array or there are two
				// continuous options, we still return missing parameter error
				if (l_tempi == p_commandArray.length - 1 || p_commandArray[l_tempi + 1].charAt(0) == '-') {
					return new LinkedList<Action>();
				}
				for (int l_tempj = l_tempi + 1; l_tempj < p_commandArray.length; l_tempj++) {
					if (p_commandArray[l_tempj].charAt(0) == '-') {
						Action l_action = new Action(p_commandArray[l_tempi].replace("-", ""), CommonTool.convertArray2String(p_commandArray, " ", l_tempi + 1, l_tempj - 1));
						l_actions.add(l_action);
						l_tempi = l_tempj;
					}
					if (l_tempj == p_commandArray.length - 1) {
						Action l_action = new Action(p_commandArray[l_tempi].replace("-", ""), CommonTool.convertArray2String(p_commandArray, " ", l_tempi + 1, l_tempj));
						l_actions.add(l_action);
						l_tempi = l_tempj;
					}
				}
			}
		}
		return l_actions;
	}
	
	/**
	 * This method will create the error controller by its error type.
	 * @param p_errorType the error type of the command
	 * @return Router representing error
	 */
	private Router createErrorRouter(String p_errorType){
		return new Router(ControllerName.ERROR, p_errorType);
	}
}