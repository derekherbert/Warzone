package warzone.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

import warzone.controller.*;
import warzone.model.*;
import warzone.view.GenericView;

public class RouterService {
	
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
	public void route(ArrayList<Router> p_routers) {
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
	private Router createErrorRouter(String p_errorType){
		return new Router(ControllerName.ERROR, p_errorType);
	}
	
	/**

	 * 
	 * e.g.
	 * editcountry -add countryID continentID -remove countryID
	 *   new Router(ControllerName.COUNTRY, "add", "countryID continentID");
	 *   new Router(ControllerName.COUNTRY, "remove", "countryID");
	 */
	public ArrayList<Router> parseCommand(String p_command) {
		ArrayList<Router> l_routerList = new ArrayList<Router>();
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
	
	
	private ArrayList<Router> parseComplexCommand(String[] commandArray) {
		ArrayList<Router> l_routers = new ArrayList<Router>();
		if(p_command == null) {
			l_routers.add(createErrorRouter());
			GenericView.printDebug("parseComplexeCommand: Null Action" );
			return l_routers;
		}
		
		String l_firstWord =  p_command.indexOf(" ")> 1 ? p_command.substring(0, p_command.indexOf(" ")) : p_command.trim().toLowerCase();
		//String l_stringAfterFirstWord = p_command.substring(p_command.indexOf(" "));
		//String[] l_orders = l_stringAfterFirstWord.split("-");
		ArrayList<Action> l_actions = parseCommandToAction(p_command);		
		 
		if(l_actions.isEmpty() ) {
			l_routers.add(createErrorRouter());
			GenericView.printDebug("parseComplexeCommand: Empty Action" );
			return l_routers;
		}
		boolean l_hasInvalidAction = false;
		ControllerName l_controllerName = ControllerName.COMMON;
		switch (l_firstWord.trim().toLowerCase()) {
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
		
		for(Action l_action: l_actions) {
			if(l_action.getAction().equals("add") || l_action.getAction().equals("remove") ) 
				l_routers.add(new Router(l_controllerName, l_action.getAction(), l_action.getParameters()));
			else 
				l_hasInvalidAction = true;
		}
		
		if(l_hasInvalidAction) {
			GenericView.printDebug("parseComplexeCommand: Include Invalid Action" );
			l_routers = new ArrayList<Router>();
			l_routers.add(createErrorRouter());
		}		
		
		return l_routers;
	}
	
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

	public ArrayList<Action> parseCommandToAction(String p_command) {
		ArrayList<Action> l_actions = new ArrayList<Action>();
		
		String l_stringAfterFirstWord = p_command.indexOf(" ") > 1 ? p_command.substring(p_command.indexOf(" ")).trim() : "";
		String[] l_subCommands = l_stringAfterFirstWord.split("-");
		boolean l_hasError = false;
		for(String l_command : l_subCommands) {
			if(l_command.equals(""))
				continue;
			String l_actionString = "" ;
			String l_parameters = "" ;
			
			l_actionString = l_command.indexOf(" ") > 1? l_command.substring(0, l_command.indexOf(" ")).toLowerCase() : "";
			l_parameters = l_command.indexOf(" ") > 1? l_command.substring(l_command.indexOf(" ")).trim() : "";
			
			if(l_actionString != "" ) {
				Action l_action = new Action(l_actionString, l_parameters);
				l_actions.add(l_action);
			}
			else {
				l_hasError = true;				
			}
		}
		if(l_hasError) {
			 l_actions = new ArrayList<Action>();
		}

		return l_actions;
	}
}