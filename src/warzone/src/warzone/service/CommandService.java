package warzone.service;

import java.util.LinkedList;
import java.util.List;

import com.alibaba.fastjson.JSONObject;

import warzone.model.ControllerName;
import warzone.model.Router;

public class CommandService {
	
	/**
	 * parse command with no option or parameter, 
	 * for example, 'showmap', 'validatemap' ...
	 * @param p_command the command entered by the player
	 * @return the list only with one Router
	 */
	public static List<Router> parseCommandWithNoneOption(String p_command) {
		List<Router> l_routerList = new LinkedList<Router>();
		l_routerList.add(new Router(ControllerName.GAME, p_command));
		return l_routerList;
	}
	
	/**
	 * parse command with any number of parameters and no option,
	 * for example, 'editmap filename' is a command with only one parameter.
	 * @param p_commands the command entered by the player
	 * @param p_parameterNames the name of each parameter
	 * @return the list of Router
	 */
	public static List<Router> parseCommandWithAnyParameters(String[] p_commands, String ... p_parameterNames) {
		List<Router> l_routerList = new LinkedList<Router>();
		// the number of parameter name must equals the length of commands String array plus one
		if (p_commands.length != p_parameterNames.length + 1) {
			return addErrorRouter(l_routerList, "wrong parameters", null);
		}
		JSONObject l_jb = new JSONObject();
		for (int i = 0; i < p_parameterNames.length; i++) {
			l_jb.put(p_parameterNames[i], p_commands[i + 1]);
		}
		l_routerList.add(new Router(ControllerName.GAME, p_commands[0], l_jb.toJSONString()));
		return l_routerList;
	}
	
	/**
	 * parse the command 'gameplayer'
	 * @param p_commands the command entered by the player
	 * @return the list of Router
	 */
	public static List<Router> parseGamePlayer(String[] p_commands) {
		List<Router> l_routerList = new LinkedList<Router>();
		List<Router> l_errorList = new LinkedList<Router>();
		// judge the correctness of option to be either "-add" or "-remove"
		for (int i = 1; i < p_commands.length; ) {
			// the number of parameter must be greater than 2
			if (p_commands.length < i + 2) {
				return addErrorRouter(l_errorList, "missing parameter", p_commands[i]);
			}
			// '-add' option
			if (p_commands[i].equals("-add")) {
				JSONObject l_jb = new JSONObject();
				l_jb.put("playername", p_commands[i + 1]);
				l_routerList.add(new Router(ControllerName.GAME, "addGamePlayer", l_jb.toJSONString()));
			}
			// '-remove' option
			else if (p_commands[i].equals("-remove")) {
				JSONObject l_jb = new JSONObject();
				l_jb.put("playername", p_commands[i + 1]);
				l_routerList.add(new Router(ControllerName.GAME, "removeGamePlayer", l_jb.toJSONString()));
			}
			// wrong option
			else {
				return addErrorRouter(l_errorList, "bad option", p_commands[i]);
			}
			i = i + 2;
		}
		return l_routerList;
	}
	
	/**
	 * parse the command 'editcontinent' and 'editcountry' in this method, because their 
	 * parameters have the same format. If there has any incorrectness, 
	 * the list only has one object whose controller name is ERROR, 
	 * and this Router also show the location of the error.
	 * @param p_commands the command "editcontinent" or "editcountry" entered by the player
	 * @param p_type either "continent" or "country"
	 * @return the list of Router
	 */
	public static List<Router> parseEditcontinentAndEditcountry(String[] p_commands, String p_type) {
		List<Router> l_routerList = new LinkedList<Router>();
		List<Router> l_errorList = new LinkedList<Router>();
		for (int i = 1; i < p_commands.length; ) {
			// judge the correctness of option to be either "-add" or "-remove"
			if (p_commands[i].equalsIgnoreCase("-add")) {
				try {
					// the number of parameter must be greater than 3
					if (p_commands.length >= i + 3) {
						JSONObject l_jb = new JSONObject();
						// the first parameter is continentID or countryID
						l_jb.put(p_type + "ID", Integer.valueOf(p_commands[i + 1]));
						// the second parameter is continentvalue or continentID
						if (p_type.equals("continent")) {
							l_jb.put("continentvalue", Integer.valueOf(p_commands[i + 2]));
						}
						else {
							l_jb.put("continentID", Integer.valueOf(p_commands[i + 2]));
						}
						l_routerList.add(new Router(ControllerName.GAME, "add" + p_type, l_jb.toJSONString()));
						i = i + 3;
					}
					else {
						return addErrorRouter(l_errorList, "missing parameter", p_commands[i]);
					}
				}
				// the parameter must be able to be parsed as integer
				catch(NumberFormatException p_exception) {
					return addErrorRouter(l_errorList, "bad parameter", p_commands[i]);
				}
			}
			else if (p_commands[i].equalsIgnoreCase("-remove")) {
				try {
					// the number of parameter must be greater than 2
					if (p_commands.length >= i + 2) {
						JSONObject l_jb = new JSONObject();
						l_jb.put(p_type + "ID", Integer.valueOf(p_commands[i + 1]));
						l_routerList.add(new Router(ControllerName.GAME, "remove" + p_type, l_jb.toJSONString()));
						i = i + 2;
					}
					else {
						return addErrorRouter(l_errorList, "missing parameter", p_commands[i]);
					}
				}
				catch(NumberFormatException p_exception) {
					return addErrorRouter(l_errorList, "bad parameter", p_commands[i + 1]);
				}
			}
			// wrong option
			else {
				return addErrorRouter(l_errorList, "bad option", p_commands[i]);
			}
		}
		return l_routerList;
	}
	
	/**
	 * parse the command 'editneighbor'. 
	 * This method has the similar logic as {@link #parseEditcontinentAndEditcountry},
	 * @param p_commands the command 'editneighbor' entered by the player
	 * @return the list of Router
	 */
	public static List<Router> parseEditneighbor(String[] p_commands) {
		List<Router> l_routerList = new LinkedList<Router>();
		List<Router> l_errorList = new LinkedList<Router>();
		for (int i = 1; i < p_commands.length; ) {
			// judge the correctness of option to be either "-add" or "-remove"
			if (p_commands[i].equalsIgnoreCase("-add")) {
				try {
					// the number of parameter must be greater than 3
					if (p_commands.length >= i + 3) {
						JSONObject l_jb = new JSONObject();
						// the first parameter is countryID
						l_jb.put("countryID", Integer.valueOf(p_commands[i + 1]));
						// the second parameter is neighborcountryID
						l_jb.put("neighborcountryID", Integer.valueOf(p_commands[i + 2]));
						l_routerList.add(new Router(ControllerName.GAME, "addNeighbor", l_jb.toJSONString()));
						i = i + 3;
					}
					else {
						return addErrorRouter(l_errorList, "missing parameter", p_commands[i]);
					}
				}
				// the parameter must be able to be parsed as integer
				catch(NumberFormatException p_exception) {
					return addErrorRouter(l_errorList, "bad parameter", p_commands[i]);
				}
			}
			else if (p_commands[i].equalsIgnoreCase("-remove")) {
				try {
					// the number of parameter must be greater than 3
					if (p_commands.length >= i + 3) {
						JSONObject l_jb = new JSONObject();
						// the first parameter is countryID
						l_jb.put("countryID", Integer.valueOf(p_commands[i + 1]));
						// the second parameter is neighborcountryID
						l_jb.put("neighborcountryID", Integer.valueOf(p_commands[i + 2]));
						l_routerList.add(new Router(ControllerName.GAME, "removeNeighbor", l_jb.toJSONString()));
						i = i + 3;
					}
					else {
						return addErrorRouter(l_errorList, "missing parameter", p_commands[i]);
					}
				}
				// the parameter must be able to be parsed as integer
				catch(NumberFormatException p_exception) {
					return addErrorRouter(l_errorList, "bad parameter", p_commands[i]);
				}
			}
		}
		return l_routerList;
	}
	
	/**
	 * add the error Router to the router list
	 * @param p_routerList the Router list
	 * @param p_errorType error type of the command
	 * @param p_location the location where the error occurs
	 * @return
	 */
	protected static List<Router> addErrorRouter(List<Router> p_routerList, String p_errorType, String p_location) {
		JSONObject l_jb = new JSONObject();
		l_jb.put("errorType", p_errorType);
		if (p_location != null) {
			l_jb.put("location", p_location);
		}
		p_routerList.add(new Router(ControllerName.ERROR, "commandError", l_jb.toJSONString()));
		return p_routerList;
	}
}
