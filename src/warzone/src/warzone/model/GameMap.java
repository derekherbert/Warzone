package warzone.model;

import java.util.List;


/**
 *	An undirected graph data structure used to hold all the countries and their neighbors  
 */
public class GameMap {

	//https://www.geeksforgeeks.org/graph-and-its-representations/
	private List<Continent> continents;
	
	private List<Country> countries;
	
	private int[][] neighborMetrix;
	
}
