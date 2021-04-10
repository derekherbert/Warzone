package warzone.model;
import java.util.List;
import java.util.Random;


/**
 *	define of the AggressiveStrategy
 */
public class AggressiveStrategy extends PlayerStrategy {	
	
	/**
	 * constructor of AggressiveStrategy
	 * @param p_player given Player
	 */
	AggressiveStrategy(Player p_player){
		super(p_player); 
	}
	
	/**
	 * implementation of createOrder
	 * @return Order
	 */
	public Order createOrder() {
		Order l_order=null;
		if(!this.d_player.getIsAlive())
			return null;
		//deploy to strongest country
		if(this.d_player.getArmiesToDeploy()>0) {
			Country l_strongestCountry=null;			
			int l_maxArmyNum=-1;
			for(Country c:this.d_player.getConqueredCountries().values()) {
				if(c.getArmyNumber()>l_maxArmyNum) {
					l_maxArmyNum=c.getArmyNumber();
					l_strongestCountry=c;
				}
			}
			l_order = new DeployOrder(this.d_player, l_strongestCountry, this.d_player.getArmiesToDeploy());
			return l_order;
		}
		//there were no army to deploy, then aggregation of forces
		for(Country c1:this.d_player.getConqueredCountries().values()) {
			if(c1.getArmyNumber()>0) {
				Country l_maxArmyConqueredNeighbor=null;
				int l_maxArmyNum=-1;
				for(Country c2:c1.getNeighbors().values()) {
					if(c2.getOwner()==this.d_player&&c2.getArmyNumber()>l_maxArmyNum) {
						l_maxArmyNum=c2.getArmyNumber();
						l_maxArmyConqueredNeighbor=c2;
					}
				}
				if(l_maxArmyConqueredNeighbor==null)
					continue;
				else {
					if(l_maxArmyConqueredNeighbor.getArmyNumber()>c1.getArmyNumber()) {
						l_order=new AdvanceOrder(this.d_player, c1, l_maxArmyConqueredNeighbor, c1.getArmyNumber());
					}else {
						l_order=new AdvanceOrder(this.d_player, l_maxArmyConqueredNeighbor, c1, l_maxArmyConqueredNeighbor.getArmyNumber());
					}
					return l_order;
				}
			}
		}
		//The forces were aggregated,then attack
		Country l_strongestCountry=null;			
		int l_maxArmyNum=-1;
		for(Country c:this.d_player.getConqueredCountries().values()) {
			if(c.getArmyNumber()>l_maxArmyNum) {
				l_maxArmyNum=c.getArmyNumber();
				l_strongestCountry=c;
			}
		}
		for(Country c:l_strongestCountry.getNeighbors().values()) {
			if(c.getOwner()!=this.d_player) {
				l_order=new AdvanceOrder(this.d_player, l_strongestCountry, c, l_strongestCountry.getArmyNumber());
				return l_order;
			}
		}
		//The neighbors of the strongest country are all conquered,then move forces randomly
		Random l_ran=new Random();
		int l_moveTo=l_ran.nextInt(l_strongestCountry.getNeighbors().size());
		l_order=new AdvanceOrder(this.d_player, l_strongestCountry, (Country) l_strongestCountry.getNeighbors().values().toArray()[l_moveTo], l_strongestCountry.getArmyNumber());
		return l_order;
	}

}
