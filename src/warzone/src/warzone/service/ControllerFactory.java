package warzone.service;

import warzone.controller.*;

public class ControllerFactory {
	private static CommonController COMMON_CONTROLLER;
	private static final MapController MapController = new MapController();
	private static final StartupController StartupController = new StartupController();
	private static final ContinentController ContinentController = new ContinentController();
	private static final CountryController CountryController = new CountryController();
	private static final NeighborController NeighborController = new NeighborController();
	
	public static CommonController getCommonController() {
		if(COMMON_CONTROLLER == null)
			COMMON_CONTROLLER = new CommonController();
		return COMMON_CONTROLLER;
	};
	
	public static MapController getMapController() {
		return MapController;
	};
	
	public static StartupController getStartupController() {
		return StartupController;
	};
	
	public static ContinentController getContinentController() {
		return ContinentController;
	};
	
	public static CountryController getCountryController() {
		return CountryController;
	};
	
	public static NeighborController getNeighborController() {
		return NeighborController;
	};
}
