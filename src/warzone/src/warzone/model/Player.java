package warzone.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import warzone.service.CommonTool;


public class Player {

	private String d_name;
	private Map<Integer, Country> d_conqueredCountries;
	private Queue<Order> d_orders;
	private int d_armyNumber = 0;
	private int d_armyRemainingNumber = 0;
	private boolean d_isSurvived = true;
	
	public Player(String p_name) {
		
		d_name = p_name;
		d_conqueredCountries = new HashMap<Integer, Country>();
		d_orders = new LinkedList<Order>();
	}
	
	public String getName() {
		return d_name;
	}

	public void setName(String p_name) {
		this.d_name = p_name;
	}

	public Map<Integer, Country> getConqueredCountries() {
		return d_conqueredCountries;
	}

	public Queue<Order> getOrders() {
		return d_orders;
	}
	
	public int getArmyNumber() {
		return d_armyNumber;
	}

	public void setArmyNumber(int p_armyNumber) {
		this.d_armyNumber = p_armyNumber;
	}
	
	public int getArmyRemainingNumber() {
		return d_armyRemainingNumber;
	}

	public void setArmyRemainingNumber(int p_armyRemainingNumber) {
		this.d_armyRemainingNumber = p_armyRemainingNumber;
	}	
	
	public boolean getIsSurvived() {
		return d_isSurvived;
	}

	public void setIsSurvived(boolean p_isSurvived) {
		this.d_isSurvived = p_isSurvived;
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
		if( this.d_armyRemainingNumber == 0 )
			return ;
		
		//ask user to enter the command or generate the command automatically. ???????????????????????????????
		List<Integer> l_countryKeys = new ArrayList(d_conqueredCountries.keySet());
		Integer l_countryKey = l_countryKeys.get( CommonTool.getRandomNumber(0, (l_countryKeys.size() -1 )) );
		
		Country l_country = d_conqueredCountries.get(l_countryKey);
		int l_armyNumber =  CommonTool.getRandomNumber(0, this.d_armyRemainingNumber);
		
		if(l_country != null)
		{
			DeployOrder l_order = new DeployOrder(this, l_country, l_armyNumber );
			this.d_orders.add(l_order);
		}		
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
	
	public void assignReinforcements() {		

	}	
	
}
