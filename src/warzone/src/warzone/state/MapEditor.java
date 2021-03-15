package warzone.state;
import warzone.service.*;
import warzone.model.*;
import warzone.view.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 *	ConcreteState of the State pattern. In this example, defines behavior 
 *  for commands that are valid in this state, and for the others signifies  
 *  that the command is invalid. 
 */
public class MapEditor extends Phase {

	private MapService d_mapService;

	/**
	 * Constructor for MapEditor
	 * @param p_ge Game Engine
	 */
	public MapEditor(GameEngine p_ge) {
		super(p_ge);
		d_mapService = new MapService(d_gameContext);
	}

	/**
	 *  Call this method to go the the next state in the sequence. 
	 */
	public void next() {
		d_gameEngine.setPhase(new Startup(d_gameEngine));
	}
	
	 public void addContinent(String p_parameters){
		 //todo
	 }	
	 public void removeContinent(String p_parameters) {
		 //todo
	 }	
	 public void addCountry (String p_parameters) {
		 //todo
	 }	
	 public void removeCountry(String p_parameters) {
		 //todo
	 }	
	 public void addNeighbor (String p_parameters) {
		 //todo
	 }		
	 public void removeNeighbor (String p_parameters) {
		 //todo
	 }	
	 
	/**
	 * Performs the action for the user command: showmap
	 *
	 * Displays the map as text, showing all continents and countries and their respective neighbors.
	 */
	public void showMap() {
		MapView.printMap(d_gameContext);
	}

	/**
	 * Performs the action for the user command: savemap filename
	 *
	 * Save a map to a text file exactly as edited (using the "domination" game map format).
	 * @param p_fileName the filename
	 * @return true if successfully save the map, otherwise return false
	 * @throws IOException the exception of saving files
	 */
	 public boolean saveMap(String p_fileName) throws IOException {

		 // validate if the filename is legal
		 if(p_fileName == null || p_fileName.trim().isEmpty() || p_fileName.trim().length() > 20 ) {
			 GenericView.printError("InValid File Name, please type a valid file name, with length less than 20.");
			 return false;
		 }

		 if(! d_mapService.validateMap(d_gameContext) ) {
			 GenericView.printError("InValid map, please check the map.");
			 return false;
		 }

		 // call mapService to save the map and return the path
		 p_fileName = p_fileName.trim();
		 try{
			 if(d_mapService.saveMap(p_fileName)) {
				 GenericView.printSuccess("Map was saved in :" + this.d_gameContext.getMapfolder() + p_fileName );
				 return true;
			 }
			 else {
				 GenericView.printError("Exception occured when saving the map, please valid the file name or contact the Administrator.");
				 return false;
			 }
		 }
		 catch(Exception ex) {
			 GenericView.printError("Exception occured when saving the map. " + ex.toString());
			 return false;
		 }
	 }

	/**
	 * Performs the action for the user command: editmap filename
	 *
	 * Load a map from an existing "domination" map file,
	 * or create a new map from scratch if the file does not exist
	 * @param p_fileName the filename
	 * @return true if successfully edit the map, otherwise return false
	 */
	 public boolean editMap (String p_fileName) {
		 return d_mapService.editMap(p_fileName);
	 }

	/**
	 * Performs the action for the user command: validatemap
	 *
	 * Verification of map correctness. The map should be automatically validated upon loading
	 * and before saving (at least 3 types of incorrect maps). The validatemap command can be
	 * triggered any time during map editing.
	 * @return true if it is a valid map, otherwise return false
	 */
	 public boolean validateMap() {
		 if(! d_mapService.validateMap(d_gameContext) ) {
			 GenericView.printError("It is not a connected map.");
			 return false;
		 }
		 else {
			 GenericView.printSuccess("Yeah! You got a connected map!");
			 return true;
		 }
	 }	

	 public void addPlayer(String p_playerName) {
		 printInvalidCommandMessage();
	 }	
	 public void removePlayer(String p_playerName){
		 printInvalidCommandMessage();
	 }	
	 public void loadMap(String p_fileName){
		 printInvalidCommandMessage();
	 }		
	 public void populatecountries(){
		 printInvalidCommandMessage();
	 }		
	 public void reinforcement(){
		 printInvalidCommandMessage();
	 }	
 
	public void issueOrder(){
		 printInvalidCommandMessage();
	 }	
	public void executeOrder(){
		 printInvalidCommandMessage();
	 }	
	
	

}
