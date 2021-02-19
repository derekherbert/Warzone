package warzone.service;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import warzone.controller.*;
import warzone.model.*;
import warzone.view.GenericView;

public class RouterService {
	
	/**
	 * This method will parse console commands entered by the user and call the corresponding controller by controller name
	 * @param p_router the Router parsed from the command
	 * @throws IOException 
	 */
	public void route(Router p_router) throws IOException{
		ControllerFactory l_controllerFactory = ControllerFactory.getControllerFactory();
		switch(p_router.getControllerName()) {
			case COMMON:
				switch(p_router.getActionName()) {
					case "welcome":
						CommonController l_commonController = l_controllerFactory.getCommonController();
						l_commonController.welcome(p_router.getActionParameters());
						break;
				}
				break;
			case CONTINENT:
				ContinentController l_continentController= l_controllerFactory.getContinentController();

				switch(p_router.getActionName()) {
					case "add":
						GenericView.printDebug("route: CONTINENT-add- " + p_router.getActionParameters());
						l_continentController.addContinent(p_router.getActionParameters());						
						break;
					case "remove":
						GenericView.printDebug("route: CONTINENT-remove-" + p_router.getActionParameters());
						l_continentController.removeContinent(p_router.getActionParameters());
						break;
				}
				break;
			case MAP:
				MapController l_mapController= l_controllerFactory.getMapController();

				switch(p_router.getActionName().toLowerCase()) {
					case "savemap":
						l_mapController.saveMap(p_router.getActionParameters());
						break;
					case "editmap":
						l_mapController.editMap(p_router.getActionParameters());
						break;
					case "showmap":
						l_mapController.showMap();
						break;
					case "validatemap":
						l_mapController.validateMap();
						break;
				}
				break;
			case COUNTRY:
				CountryController l_countryController= l_controllerFactory.getCountryController();

				switch(p_router.getActionName()) {
					case "add":
						l_countryController.addCountry(p_router.getActionParameters());
						break;
					case "remove":
						l_countryController.removeCountry(p_router.getActionParameters());
						break;
				}
				break;
			case NEIGHBOR:
				NeighborController l_neighborController= l_controllerFactory.getNeighborController();

				switch(p_router.getActionName()) {
					case "add":
						l_neighborController.addNeighbor(p_router.getActionParameters());
						break;
					case "remove":
						l_neighborController.removeNeighbor(p_router.getActionParameters());
						break;
				}
				break;
			//TODO add error controllerp
		}	
		
	}
	
	/**
	 * This class will parse console commands entered by the user and call the corresponding Java method(s)
	 * passing any command line arguments as parameters
	 * 
	 */
//	public Router parseCommand(String p_command) {
//		//todo
//		return null;
//	}
	
	/**

	 * 
	 * e.g.
	 * editcountry -add countryID continentID -remove countryID
	 *   new Router(ControllerName.COUNTRY, "add", "countryID continentID");
	 *   new Router(ControllerName.COUNTRY, "remove", "countryID");
	 */
	public void route(List<Router> p_routers) {
		if(p_routers != null) {
			p_routers.forEach((router) ->{
				try {
					route(router);
				}
				catch(Exception ex){					
				}
			});			
		}
		else {
			//exception
		}
	}
	
	/**
	 * This method parse the command entered by the player, and construct corresponding Router
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
		GenericView.printDebug("parseCommand: start to work on command: " + p_command);
		
		// remove prefix whitespace and convert the String to lower case 
		p_command = p_command.toLowerCase().trim();
		
		// null command only with whitespace
		if (p_command.length() == 0) {
			l_routerList.add(createErrorRouter(ErrorType.MISSING_COMMAND.name()));
			return l_routerList;
		}
		
		// split command with any number of whitespace
		String[] l_commandArray = p_command.split("\\s+");
		
		String l_firstWord = l_commandArray[0];
		// TODO move these commands into the properties file
		String l_complexeCommand = "editcontinent,editcountry,editneighbor,gameplayer";
		if(l_complexeCommand.indexOf(l_firstWord) > -1 ) {
			//complex command with multiple routers
			GenericView.printDebug("parseCommand: start to work on complexe command: " + p_command);
			l_routerList = parseComplexCommand(l_commandArray);
		}
		else {
			//simple command with only one router
			GenericView.printDebug("parseCommand: start to work on simple command: " + p_command);
			l_routerList.add(parseSimpleCommand(l_commandArray));				
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
			GenericView.printDebug("parseComplexeCommand: Empty Action" );
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
				l_controllerName = ControllerName.GAMEPLAYER;				
				break;
		}
		
		// if the action is not equal to 'add' or 'remove', we return an error router
		for(Action l_action: l_actions) {
			//TODO add it in the property file
			String l_actionArray = "add,remove";
			if(l_actionArray.indexOf("l_action.getAction()") > -1) { 
				l_routers.add(new Router(l_controllerName, l_action.getAction(), CommonTool.convertArray2String(l_action.getParameters(), " ")));
			}
			else {
				l_routers = new LinkedList<Router>();
				l_routers.add(createErrorRouter(ErrorType.BAD_OPTION.toString()));
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
			case  "showmap":
				l_router = new Router(ControllerName.MAP, "showmap");
				break;
			case  "validatemap":
				l_router =  new Router(ControllerName.MAP, "validatemap");
				break;
			case  "assigncountries":
				l_router =  new Router(ControllerName.STARTUP, "assigncountries");
				break;
			case  "savemap":
				if(p_commandArray.length == 2 ) {
					l_router =  new Router(ControllerName.MAP, "savemap", p_commandArray[1]);
				}
				else {
					return createErrorRouter(ErrorType.MISSING_PARAMETER.toString());
				}
				break;
			case  "editmap":
				if(p_commandArray.length == 2 ) {
					l_router =  new Router(ControllerName.MAP, "editmap", p_commandArray[1]);
				}
				else {
					return createErrorRouter(ErrorType.MISSING_PARAMETER.toString());
				}
				break;
			case  "loeadmap":
				if(p_commandArray.length == 2 ) {
					l_router =  new Router(ControllerName.MAP, "loadmap", p_commandArray[1]);
				}
				else {
					return createErrorRouter(ErrorType.MISSING_PARAMETER.toString());
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
		for(int i = 1; i < p_commandArray.length; i++) {
			// judge that if p_commandArray[i] is an option
			if(p_commandArray[i].charAt(0) == '-') {
				// if an option is the last element of the array or there are two
				// continuous options, we still return missing parameter error
				if (i == p_commandArray.length - 1 || p_commandArray[i + 1].charAt(0) == '-') {
					return new LinkedList<Action>();
				}
				for (int j = i + 1; j < p_commandArray.length; j++) {
					if (p_commandArray[j].charAt(0) == '-' || j == p_commandArray.length - 1) {
						Action l_action = new Action(p_commandArray[i], Arrays.copyOfRange(p_commandArray, i, j - 1));
						l_actions.add(l_action);
						i = j;
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