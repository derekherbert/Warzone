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
	 * @param p_command the command entered by the player
	 * @return the list of Router
	 */
	public List<Router> parseCommand(String p_command) {
		List<Router> l_routerList;
		// remove prefix whitespace
		p_command = p_command.trim();
		
		// null command only with whitespace
		if (p_command.length() == 0) {
			l_routerList = new LinkedList<Router>();
			l_routerList.add(new Router(ControllerName.ERROR, "nullCommand"));
			return l_routerList;
		}
		
		// split command with any whitespace
		String[] l_commands = p_command.split("\\s+");
		
		// judge command
		if (l_commands[0].equalsIgnoreCase("showmap")) {
			l_routerList = CommandService.parseCommandWithNoneOption(l_commands[0]);
		}
		else if (l_commands[0].equalsIgnoreCase("validatemap")) {
			l_routerList = CommandService.parseCommandWithNoneOption(l_commands[0]);
		}
		else if (l_commands[0].equalsIgnoreCase("assigncountries")) {
			l_routerList = CommandService.parseCommandWithNoneOption(l_commands[0]);
		}
		else if (l_commands[0].equalsIgnoreCase("editcontinent")) {
			l_routerList = CommandService.parseEditcontinentAndEditcountry(l_commands, "continent");
		}
		else if (l_commands[0].equalsIgnoreCase("editcountry")) {
			l_routerList = CommandService.parseEditcontinentAndEditcountry(l_commands, "country");
		}
		else if (l_commands[0].equalsIgnoreCase("editneighbor")) {
			l_routerList = CommandService.parseEditneighbor(l_commands);
		}
		else if (l_commands[0].equalsIgnoreCase("gameplayer")) {
			l_routerList = CommandService.parseGamePlayer(l_commands);
		}
		else if (l_commands[0].equalsIgnoreCase("savemap")) {
			l_routerList = CommandService.parseCommandWithAnyParameters(l_commands, "filename");
		}
		else if (l_commands[0].equalsIgnoreCase("editmap")) {
			l_routerList = CommandService.parseCommandWithAnyParameters(l_commands, "filename");
		}
		else if (l_commands[0].equalsIgnoreCase("loadmap")) {
			l_routerList = CommandService.parseCommandWithAnyParameters(l_commands, "filename");
		}
		else if (l_commands[0].equalsIgnoreCase("deploy")) {
			l_routerList = CommandService.parseCommandWithAnyParameters(l_commands, "countryID", "num");
		}
		else {
			l_routerList = new LinkedList<Router>();
			l_routerList.add(new Router(ControllerName.ERROR, "noSuchCommand"));
		}
		return null;
	}

}

