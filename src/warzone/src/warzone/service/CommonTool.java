package warzone.service;

import warzone.view.GenericView;

public class CommonTool {
	
	public static int parseInt(String p_number) {
		int l_result = -1;
		if(p_number != null) {
			try {
				 l_result = Integer.parseInt(p_number.trim());
			}
			catch(Exception ex) {
				GenericView.printError(p_number + " is not a valid Integer");
			}
		}
		return l_result;		
	}
	
	public static String[] conventToArray(String p_parameters) {
		if(p_parameters != null)
			return p_parameters.split(" ");
		else
			return new String[] {};
	}	
	
	public static String convertArray2String(String[] p_stringArray, String p_separator, int from, int to) {
		StringBuilder l_sb = new StringBuilder();
		for (int i = from; i < to + 1; i++) {
			l_sb.append(p_stringArray[i]);
			if (i < to && i < p_stringArray.length - 1) {
				l_sb.append(p_separator);
			}
		}
		return l_sb.toString();
	}
}
