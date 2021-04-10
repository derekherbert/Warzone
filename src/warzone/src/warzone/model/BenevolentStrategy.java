package warzone.model;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


/**
 *	define of the BenevolentStrategy
 */
public class BenevolentStrategy extends PlayerStrategy {	
	
	/**
	 * constructor of BenevolentStrategy
	 * @param p_player given Player
	 */
	BenevolentStrategy(Player p_player){
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
		//deploy to weakest country
		if(this.d_player.getArmiesToDeploy()>0) {
			Country l_weakestCountry=null;			
			int l_minArmyNum=Integer.MAX_VALUE;
			for(Country c:this.d_player.getConqueredCountries().values()) {
				if(c.getArmyNumber()<l_minArmyNum) {
					l_minArmyNum=c.getArmyNumber();
					l_weakestCountry=c;
				}
			}
			l_order = new DeployOrder(this.d_player, l_weakestCountry, this.d_player.getArmiesToDeploy());
			return l_order;
		}
		//move armies to reinforce its weaker country
		//sort by army number with ascending order
		List<Object> l_conqueredCountries=Arrays.asList(this.d_player.getConqueredCountries().values().toArray());
		Collections.sort(l_conqueredCountries,new Comparator<Object>() {
			@Override
			public int compare(Object obj1,Object obj2) {
				Country c1=(Country) obj1;
				Country c2=(Country) obj2;
				if (c1.getArmyNumber()>c2.getArmyNumber()) {
					return 1;
				}else if (c1.getArmyNumber()<c2.getArmyNumber()) {
					return -1;
				}else {
					return 0;
				}
			}
		});
		//move army to weaker country
		for(Object obj:l_conqueredCountries) {
			Country c1=(Country)obj;
			for(Country c2:c1.getNeighbors().values()) {
				if(c2.getArmyNumber()>c1.getArmyNumber()) {
					int diff=c2.getArmyNumber()-c1.getArmyNumber();
					if(diff>1) {
						l_order = new AdvanceOrder(this.d_player, c2, c1 , diff/2);
						return l_order;
					}
				}
			}
		}
		
		return l_order;
	}
}
