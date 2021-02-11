package warzone.service;

import java.util.LinkedList;
import java.util.List;
import com.alibaba.fastjson.*;

import warzone.controller.CommonController;
import warzone.model.*;

public class RouterService {
	
	public void route(Router p_router){
		
		switch(p_router.getControllerName()) {
			case COMMON:
				switch(p_router.getActionName()) {
					case "welcome":
						CommonController l_commonController = ControllerFactory.getCommonController();
						l_commonController.welcome();		
				}
		}
	}
	
	public void route(List<Router> p_routerList) {
		
	}
	
	/**
	 * This class will parse console commands entered by the user and call the corresponding Java method(s)
	 * passing any command line arguments as parameters
	 */
	public Router parseCommand(String p_command) {
		// remove prefix whitespace
		p_command = p_command.trim();
		
		// null command only with whitespace
		if (p_command.length() == 0) {
			return new Router(ControllerName.ERROR, "nullCommand");
		}
		
		// split command with any whitespace
		String[] l_commands = p_command.split("\\s+");
		
		// judge command
		if (l_commands[0].equalsIgnoreCase("showmap")) {
			return parseCommandWithNoneOption(l_commands[0]);
		}
		else if (l_commands[0].equalsIgnoreCase("validatemap")) {
			return parseCommandWithNoneOption(l_commands[0]);
		}
		else if (l_commands[0].equalsIgnoreCase("assigncountries")) {
			return parseCommandWithNoneOption(l_commands[0]);
		}
		else if (l_commands[0].equalsIgnoreCase("editcontinent")) {
		
		}
		else if (l_commands[0].equalsIgnoreCase("editcountry")) {
			
		}
		else if (l_commands[0].equalsIgnoreCase("editneighbor")) {
			
		}
		else if (l_commands[0].equalsIgnoreCase("savemap")) {
			
		}
		else if (l_commands[0].equalsIgnoreCase("editmap")) {
			
		}
		else if (l_commands[0].equalsIgnoreCase("loadmap")) {
			
		}
		else if (l_commands[0].equalsIgnoreCase("gameplayer")) {
			
		}
		else if (l_commands[0].equalsIgnoreCase("deploy")) {
			
		}
		else {
			return new Router(ControllerName.ERROR, "noSuchCommand");
		}
		return null;
	}
	
	private Router parseCommandWithNoneOption(String p_command) {
		return new Router(ControllerName.GAME, p_command);
	}
	
	/**
	 * parse the command 'edit continent', return the list of Router. If there has any incorrectness, 
	 * the list only has one object whose controller type is ERROR, 
	 * and this Router also show the location of the error.
	 * @param p_commands the command 'edit continent' entered by the player
	 * @return the list of Router
	 */
	private List<Router> parseEditContinent(String[] p_commands) {
		List<Router> l_routerList = new LinkedList();
		List<Router> l_errorList = new LinkedList();
		for (int i = 1; i < p_commands.length; ) {
			// judge the correctness of option to be either "-add" or "-remove"
			if (p_commands[i].equalsIgnoreCase("-add")) {
				try {
					// the number of parameter must be greater than 3
					if (p_commands.length >= i + 3) {
						JSONObject l_jb = new JSONObject();
						l_jb.put("continentID", Integer.valueOf(p_commands[i + 2]));
						l_jb.put("continentvalue", Integer.valueOf(p_commands[i + 3]));
						l_routerList.add(new Router(ControllerName.GAME, "addContinent", l_jb.toJSONString()));
						i = i + 3;
					}
					else {
						l_errorList.add(new Router(ControllerName.ERROR, "missParameter: " + p_commands[i]));
						return l_errorList;
					}
				}
				catch(NumberFormatException p_exception) {
					l_errorList.add(new Router(ControllerName.ERROR, "badParameter: " + p_commands[i + 1]));
					return l_errorList;
				}
			}
			else if (p_commands[i].equalsIgnoreCase("-remove")) {
				try {
					// the number of parameter must be greater than 2
					if (p_commands.length >= i + 2) {
						JSONObject l_jb = new JSONObject();
						l_jb.put("continentID", Integer.valueOf(p_commands[i + 2]));
						l_routerList.add(new Router(ControllerName.GAME, "removeContinent", l_jb.toJSONString()));
						i = i + 2;
					}
					else {
						l_errorList.add(new Router(ControllerName.ERROR, "missParameter: " + p_commands[i]));
						return l_errorList;
					}
				}
				catch(NumberFormatException p_exception) {
					l_errorList.add(new Router(ControllerName.ERROR, "badParameter: " + p_commands[i + 1]));
					return l_errorList;
				}
			}
			else {
				l_errorList.add(new Router(ControllerName.ERROR, "badOption: " + p_commands[i]));
				return l_errorList;
			}
		}
		return l_routerList;
	}
	
//	private Router parseCommandWithOneParameter
}

