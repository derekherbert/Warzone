package warzone.model;

import java.util.Scanner;

public class CommandScanner {
	Scanner sc = new Scanner(System.in);
	
	public String getCommand() {
		return sc.nextLine();
	}
}
