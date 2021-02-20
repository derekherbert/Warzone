package warzone.service;

import warzone.controller.*;
import warzone.model.GameContext;

public class ControllerFactory {	
	
	private static ControllerFactory CONTROLLER_FACTORY;
	private static GameContext GAME_CONTEXT;
	
	private CommonController d_commonController;
	private ContinentController d_continentController;
	private MapController d_mapController;
	private CountryController d_countryController;
	private NeighborController d_neighborController;
	private StartupController d_startupController;
	private ErrorController d_errorController;
	private GameplayController d_gameplayController;
	
	private ControllerFactory()	{	
		GAME_CONTEXT = GameContext.getGameContext();
	}
	
	public static ControllerFactory getControllerFactory() {
		if( CONTROLLER_FACTORY == null)
			CONTROLLER_FACTORY = new ControllerFactory();

		return CONTROLLER_FACTORY;
	}
	
	public CommonController getCommonController() {
		if(d_commonController == null)
			d_commonController = new CommonController(GAME_CONTEXT);
		return d_commonController;
	};
	
	public ContinentController getContinentController() {
		if(d_continentController == null)
			d_continentController = new ContinentController(GAME_CONTEXT);
		return d_continentController;
	};
	
	public MapController getMapController() {
		if(d_mapController == null)
			d_mapController = new MapController(GAME_CONTEXT);
		return d_mapController;
	};
	
	public CountryController getCountryController() {
		if(d_countryController == null)
			d_countryController = new CountryController(GAME_CONTEXT);
		return d_countryController;
	};
	
	public NeighborController getNeighborController() {
		if(d_neighborController == null)
			d_neighborController = new NeighborController(GAME_CONTEXT);
		return d_neighborController;
	};
	
	public StartupController getStartupController() {
		if(d_startupController == null)
			d_startupController = new StartupController();
		return d_startupController;
	};
	
	public ErrorController getErrorController() {
		if(d_errorController == null)
			d_errorController = new ErrorController();
		return d_errorController;
	};
	
	public GameplayController getGameplayController() {
		if(d_gameplayController == null)
			d_gameplayController = new GameplayController();
		return d_gameplayController;
	};
}