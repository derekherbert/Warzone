package warzone.controller;

import warzone.view.Console;

public class CommonController {
	
	public String welcome(String p_actionParameters) {
		String body = "Hello world";
		Console.println(body);
		return body;
	}
	

	
	public void standby() {}
}
