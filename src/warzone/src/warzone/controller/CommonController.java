package warzone.controller;

import warzone.view.Console;

public class CommonController {
	public void welcome() {
		String body = "Hello world";
		Console.println(body);
		
	}
	
	public void error(String p_message) {
		System.out.print(p_message);
	}

	
	public void standby() {}
}
