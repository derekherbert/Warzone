package warzone.view;

public class Console {
	
	public static void println(String p_text) {
		System.out.println(p_text);
	}
	
	public static void println(IRender p_content) {
		p_content.Render();
	}
}
