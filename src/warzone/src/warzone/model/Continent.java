package warzone.model;

import java.util.Set;

public class Continent {
	
	private int d_continentID;
	private String d_continentName;
	private Set<Integer> d_countries;
	
	public Continent(int p_continentID, String p_continentName) {
		
		d_continentID = p_continentID;
		d_continentName = p_continentName;
	}
	
	public int getContinentID() {
		return d_continentID;
	}
	
	public void setContinentID(int p_continentID) {
		this.d_continentID = p_continentID;
	}
	
	public String getContinentName() {
		return d_continentName;
	}
	
	public void setContinentName(String p_continentName) {
		this.d_continentName = p_continentName;
	}	
	
	public Set<Integer> getCountries() {
		return d_countries;
	}
}
