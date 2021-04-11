package warzone.model;
import java.util.List;
import java.util.Random;


/**
 *	define of the AggressiveStrategy
 */
public class AggressiveStrategy extends PlayerStrategy {	
	
	/**
	 * Armies to deploy
	 */
	private int d_armiesToDeploy;
	
	/**
	 * constructor of AggressiveStrategy
	 * @param p_player given Player
	 */
	AggressiveStrategy(Player p_player){
		super(p_player);
		d_armiesToDeploy=p_player.getArmiesToDeploy();
	}
	
	/**
	 * Get the conquered country which has maximum army number
	 * @return the strongest conquered country
	 */
	protected Country getStrongestConqueredCountry() {
		Country l_strongestCountry=null;			
		int l_maxArmyNum=-1;
		for(Country l_c:d_player.getConqueredCountries().values()) {
			if(l_c.getArmyNumber()>l_maxArmyNum) {
				l_maxArmyNum=l_c.getArmyNumber();
				l_strongestCountry=l_c;
			}
		}
		return l_strongestCountry;
	}
	
	/**
	 * implementation of createOrder
	 * @return Order
	 */
	public Order createOrder() {
		Order l_order=null;
		if(!d_player.getIsAlive())
			return null;
		//deploy to strongest country
		if(d_armiesToDeploy>0) {
			Country l_strongestCountry=getStrongestConqueredCountry();			
			l_order = new DeployOrder(d_player, l_strongestCountry, d_armiesToDeploy);
			d_armiesToDeploy-=d_armiesToDeploy;
			return l_order;
		}
		//attack
		Country l_strongestCountry=getStrongestConqueredCountry();			
		for(Country l_c:l_strongestCountry.getNeighbors().values()) {
			if(l_c.getOwner()!=d_player&&d_player.getArmyNumber()>2*l_c.getArmyNumber()) {
				l_order=new AdvanceOrder(d_player, l_strongestCountry, l_c, 2*l_c.getArmyNumber());
				d_player.setHasFinisedIssueOrder(true);
				return l_order;
			}
		}
		//The neighbors of the strongest country are all conquered,then move forces randomly
		Random l_ran=new Random();
		int l_moveTo=l_ran.nextInt(l_strongestCountry.getNeighbors().size());
		l_order=new AdvanceOrder(d_player, l_strongestCountry, (Country) l_strongestCountry.getNeighbors().values().toArray()[l_moveTo], l_strongestCountry.getArmyNumber());
		return l_order;
	}

}
