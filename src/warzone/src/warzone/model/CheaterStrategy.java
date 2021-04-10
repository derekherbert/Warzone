package warzone.model;
import java.util.List;


/**
 *	define of the CheaterStrategy
 */
public class CheaterStrategy extends PlayerStrategy {	
	
	/**
	 * constructor of CheaterStrategy
	 * @param p_player given Player
	 */
	CheaterStrategy(Player p_player){
		super(p_player); 
	}
	
	/**
	 *  implementation of createOrder
	 * @return
	 */
	public Order createOrder() {
		
		for(Country c1:this.d_player.getConqueredCountries().values()) {
			boolean l_hasEnemyNeighbor=false;
			for(Country c2:c1.getNeighbors().values()) {
				if(c2.getOwner()!=this.d_player) {
					l_hasEnemyNeighbor=true;
					if(c1.getArmyNumber()>2*c2.getArmyNumber()) {
						Order l_order=new AdvanceOrder(this.d_player, c1, c2, 2*c2.getArmyNumber());
						l_order.execute();
					}
				}
			}
			if(l_hasEnemyNeighbor) {
				c1.setArmyNumber(2*c1.getArmyNumber());
			}
		}
			
		return null;
	}
}
