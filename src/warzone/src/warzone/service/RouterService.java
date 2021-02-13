package warzone.service;

import java.util.Iterator;
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
	
	/**
	 * route the command parsed by the command parser to different controller
	 * @param p_routerList the command list parsed by the command parser
	 */
	public void route(List<Router> p_routerList) {
		if (p_routerList == null || p_routerList.size() == 0) {
			// TODO Auto-generated method stub
			return;
		}
		if (p_routerList.get(0).getControllerName().equals(ControllerName.ERROR)) {
			if (p_routerList.get(0).getActionName().equals("commandError")) {
				CommonController l_commonController = ControllerFactory.getCommonController();
				l_commonController.error(p_routerList.get(0).getActionName());
			}
		}
		else if (p_routerList.get(0).getControllerName().equals(ControllerName.GAME)) {
			Iterator<Router> l_routerIterator = p_routerList.iterator();
			while(l_routerIterator.hasNext()) {
				Router l_currentRouter = l_routerIterator.next();
				if (l_currentRouter.getActionName().equals("showmap")) {
					ControllerFactory.getMapController().showMap();
				}
				else if (l_currentRouter.getActionName().equals("validatemap")) {
					ControllerFactory.getMapController().validateMap();
				}
				else if (l_currentRouter.getActionName().equals("assigncountries")) {
					ControllerFactory.getStartupController().assignCountries();
				}
				else if (l_currentRouter.getActionName().equals("addcontinent")) {
					JSONObject l_jb = JSONObject.parseObject(l_currentRouter.getActionParameters());
					ControllerFactory.getContinentController().addContinent(Integer.valueOf(l_jb.getInteger("continentID")), l_jb.getString("continentvalue"));
				}
				else if (l_currentRouter.getActionName().equals("removecontinent")) {
					JSONObject l_jb = JSONObject.parseObject(l_currentRouter.getActionParameters());
					ControllerFactory.getContinentController().removeContinent(Integer.valueOf(l_jb.getInteger("continentID")));
				}
				else if (l_currentRouter.getActionName().equals("addcountry")) {
					JSONObject l_jb = JSONObject.parseObject(l_currentRouter.getActionParameters());
					ControllerFactory.getCountryController().addCountry(l_jb.getInteger("countryID"), l_jb.getInteger("continentID"));
				}
				else if (l_currentRouter.getActionName().equals("removecountry")) {
					JSONObject l_jb = JSONObject.parseObject(l_currentRouter.getActionParameters());
					ControllerFactory.getCountryController().removeCountry(l_jb.getInteger("countryID"));
				}
				else if (l_currentRouter.getActionName().equals("addGamePlayer")) {
					// TODO Auto-generated method stub
				}
				else if (l_currentRouter.getActionName().equals("removeGamePlayer")) {
					// TODO Auto-generated method stub
				}
				else if (l_currentRouter.getActionName().equals("addNeighbor")) {
					JSONObject l_jb = JSONObject.parseObject(l_currentRouter.getActionParameters());
					ControllerFactory.getNeighborController().addNeighbor(l_jb.getInteger("countryID"), l_jb.getString("neighborcountryID"));
				}
				else if (l_currentRouter.getActionName().equals("removeNeighbor")) {
					JSONObject l_jb = JSONObject.parseObject(l_currentRouter.getActionParameters());
					ControllerFactory.getNeighborController().removeNeighbor(l_jb.getInteger("countryID"), l_jb.getString("neighborcountryID"));
				}
				else if (l_currentRouter.getActionName().equals("savemap")) {
					JSONObject l_jb = JSONObject.parseObject(l_currentRouter.getActionParameters());
					ControllerFactory.getMapController().saveMap(l_jb.getString("filename"));
				}
				else if (l_currentRouter.getActionName().equals("editmap")) {
					JSONObject l_jb = JSONObject.parseObject(l_currentRouter.getActionParameters());
					ControllerFactory.getMapController().editMap(l_jb.getString("filename"));
				}
				else if (l_currentRouter.getActionName().equals("loadmap")) {
					// TODO Auto-generated method stub

				}
				else if (l_currentRouter.getActionName().equals("deploy")) {
					// TODO Auto-generated method stub

				}
				else {
					// TODO Auto-generated method stub
				}
				
			}
		}
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
			CommandService.addErrorRouter(l_routerList, "nullCommand", null);
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
			CommandService.addErrorRouter(l_routerList, "no such command", l_commands[0]);
		}
		return l_routerList;
	}
}

