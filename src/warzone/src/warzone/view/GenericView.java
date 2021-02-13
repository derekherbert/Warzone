package warzone.view;

import warzone.model.Render;

/*
 * for specific ui, should create dedicated view class.
 * */
public class GenericView {
	
	public static void println(String p_text) {
		System.out.println(p_text);
	}
	
	public static void printHelp(String p_text) {
		System.out.println("HELP:" + p_text );
	}
	
	public static void printError(String p_text) {
		System.out.println("ERROR:" + p_text);
	}
	public static void printDone(String p_text) {
		System.out.println("Done:" + p_text);
	}
	
	public static void println(Render p_content) {
		p_content.render();
	}
}
