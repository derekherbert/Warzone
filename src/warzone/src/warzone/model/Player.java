package warzone.model;

import java.util.Map;
import java.util.Queue;


public class Player {

	private String d_name;
	private Map<Integer, Integer> d_conqueredCountries;
	private Queue<Order> d_orders;
	
	public String getName() {
		return d_name;
	}

	public void setName(String p_name) {
		this.d_name = p_name;
	}

	public Map<Integer, Integer> getConqueredCountries() {
		return d_conqueredCountries;
	}

	public Queue<Order> getOrders() {
		return d_orders;
	}


	/**
	 * The GameEngine class calls the issue_order() method of the Player. This method will wait for the following 
	 * command, then create a deploy order object on the players list of orders, then reduce the number of armies in the 
	 * players reinforcement pool. The game engine does this for all players in round-robin fashion until all the players 
	 * have placed all their reinforcement armies on the map.
	 * 
	 * Issuing order command: deploy countryID num (until all reinforcements have been placed)
	 */
	public void issue_order() {
		
		// TODO Auto-generated method stub
	}
	
	
	/**
	 * The GameEngine calls the next_order() method of the Player. Then the Order objects execute() method is called, 
	 * which will enact the order. 
	 * 
	 * @return
	 */
	public Order next_order() {
		
		// TODO Auto-generated method stub
		
		return null;
	}
}
