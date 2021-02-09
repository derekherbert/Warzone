package warzone.model;

import java.util.Set;

public class Country {
	
	private int d_countryID;
	private String d_countryName;
	private int d_ownerID;
	private int d_deployedForces;
	private Set<Integer> d_neighbors;
	
	public int getCountryID() {
		return d_countryID;
	}
	
	public void setCountryID(int p_countryID) {
		this.d_countryID = p_countryID;
	}
	
	public String getCountryName() {
		return d_countryName;
	}
	
	public void setCountryName(String p_countryName) {
		this.d_countryName = p_countryName;
	}	
	
	public int getOwnerID() {
		return d_ownerID;
	}

	public void setOwnerID(int p_ownerID) {
		this.d_ownerID = p_ownerID;
	}

	public int getDeployedForces() {
		return d_deployedForces;
	}

	public void setDeployedForces(int p_deployedForces) {
		this.d_deployedForces = p_deployedForces;
	}

	public Set<Integer> getNeighbors() {
		return d_neighbors;
	}
}
