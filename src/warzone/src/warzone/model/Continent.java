package warzone.model;

import java.util.ArrayList;
import java.util.List;

public class Continent implements IRender {
	
	private int d_continentID;
	private String d_ContinentName;
	private String color;
	private List<Country> countries;
	
	public Continent(int p_ContinentID, String p_ContinentName) {
		d_continentID = p_ContinentID;
		d_ContinentName = p_ContinentName;
	}
	
	public int getContinentID() {
		return d_continentID;
	}
	
	public void setContinentID(int continentID) {
		this.d_continentID = continentID;
	}
	
	public String getContinentName() {
		return continentName;
	}
	
	public void setContinentName(String continentName) {
		this.continentName = continentName;
	}	
	
	@Override
	public void Render() {
		// todo: 
		//e.g.  System.out.println("ID" + getContinentID());
	}
}
