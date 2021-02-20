package warzone.model;

import java.io.IOException;
import java.util.Properties;

import warzone.view.GenericView;

public class WarzoneProperties {

	//Properties
	public static String GAME_MAP_DIRECTORY;
	
	//Class variables
	private static WarzoneProperties WARZONE_PROPERTIES;
	private static Properties PROPERTIES;
	
	//Create singlton
	private WarzoneProperties() {
		
		try {

			PROPERTIES = new Properties();
			PROPERTIES.load(getClass().getClassLoader().getResourceAsStream("config.properties"));
			
			loadProperties();
		
		} catch (IOException e) {

			GenericView.printError("Error loading properties file.");
		}
	}
	
	//Return singleton
	public static WarzoneProperties getWarzoneProperties() {
		
		if(WARZONE_PROPERTIES == null) {
			WARZONE_PROPERTIES = new WarzoneProperties();
		}
		return WARZONE_PROPERTIES;
	}
	
	/**
	 * Load each property from config.properties into the java variables 
	 */
	private static void loadProperties() {
		
		GAME_MAP_DIRECTORY = PROPERTIES.getProperty("gameMapDirectory");
	}
}